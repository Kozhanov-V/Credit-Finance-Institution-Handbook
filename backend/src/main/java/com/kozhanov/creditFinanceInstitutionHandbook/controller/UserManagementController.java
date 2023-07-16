package com.kozhanov.creditFinanceInstitutionHandbook.controller;

import com.kozhanov.creditFinanceInstitutionHandbook.config.WebSecurityConfig;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;
import com.kozhanov.creditFinanceInstitutionHandbook.model.users.Confirmation;
import com.kozhanov.creditFinanceInstitutionHandbook.model.users.User;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.auth.ConfirmationRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.auth.RoleReposiroty;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.auth.UserRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.BICDirectoryEntryRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.security.*;
import com.kozhanov.creditFinanceInstitutionHandbook.service.BICDirectoryEntryService;
import com.kozhanov.creditFinanceInstitutionHandbook.service.ConfirmationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@Api(value = "User Management Controller", description = "Operations for User Authentication and Management")
public class UserManagementController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleReposiroty roleReposiroty;

    @Autowired
    private ConfirmationService confirmationService;

    @Autowired
    private BICDirectoryEntryService bicDirectoryEntryService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;

    @ApiOperation(value = "Authenticate a user and generate a JWT token", response = JwtResponse.class)
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            System.out.println("Incorrect username or password");
            throw new Exception("Incorrect username or password", e);
        }catch (Exception e) {
            System.out.println("General exception");
            System.out.println(e.getMessage());
            throw new Exception("Account locked", e);
        }
        final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println(jwt);
        return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.OK);
    }

    @PostMapping("/api/favorites/add/{bic}")
    @ApiOperation(value = "Add a BIC Directory Entry to a User's favorites")
    public ResponseEntity<?> addBicDirectoryEntry(@PathVariable int bic, @RequestBody String username) {
        username = username.substring(0,username.length()-1);

        User user = userRepository.findByUsername(username);
        BICDirectoryEntry bicDirectoryEntry = bicDirectoryEntryService.findByBic(bic);
        user.getBicDirectoryEntries().add(bicDirectoryEntry);
        userRepository.save(user);

        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }


    @ApiOperation(value = "Retrieve the favorite entries of a User", response = Set.class)
    @GetMapping("/api/favorites/get")
    public ResponseEntity<?> getFavoritesEntry(@RequestParam(name = "token") String token) {
        try {
            if(token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            String username = claims.getBody().getSubject();  // Декодирование JWT

            MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(username);

            // Получение информации о избранных записях
            Set<BICDirectoryEntry> favorites = userDetails.getBicDirectoryEntries();

            return new ResponseEntity<>(favorites, HttpStatus.OK);
        } catch (JwtException | IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation(value = "Remove a BIC Directory Entry from a User's favorites")
    @PostMapping("/api/favorites/delete/{bic}")
    public ResponseEntity<?> deleteBicDirectoryEntry(@PathVariable int bic, @RequestBody String username) {
        username = username.substring(0,username.length()-1);

        User user = userRepository.findByUsername(username);
        BICDirectoryEntry bicDirectoryEntry = bicDirectoryEntryService.findByBic(bic);
        if(user.getBicDirectoryEntries().contains(bicDirectoryEntry)){
            user.getBicDirectoryEntries().remove(bicDirectoryEntry);
            userRepository.save(user);
        }



        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }

    @ApiOperation(value = "Check if a JWT token is valid")
    @PostMapping("/api/token/check")
    public ResponseEntity<?> checkTokenValidations(@RequestBody String token) {

        try {
            if(token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
                Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);


            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (JwtException | IllegalArgumentException e) {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
    }


    @ApiOperation(value = "Register a new User")
    @PostMapping("/api/register")
    public ResponseEntity register(@RequestBody User user) {

        if (userRepository.findByUsername(user.getUsername())!=null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken");
        }
        user.setPassword(WebSecurityConfig.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);

        String token = "QWERTY";
        Confirmation confirmation = new Confirmation(token,user);
        confirmationService.save(confirmation);
        return new ResponseEntity<>("A confirmation code has been sent to your phone number", HttpStatus.OK);
    }

    @ApiOperation(value = "Confirm a User's account")
    @PostMapping("/api/confirm")
    public ResponseEntity confirmAccount(@RequestBody Map<String, String> payload) {
        String token = payload.get("code");
        Optional<Confirmation> optionalConfirmation = confirmationService.findByToken(token);

        if (optionalConfirmation.isPresent()) {
            Confirmation confirmation = optionalConfirmation.get();
            User user = confirmation.getUser();
            user.addRole(roleReposiroty.findByName("ROLE_USER"));
            user.setActive(true);
            userRepository.save(user);
            confirmationService.delete(confirmation);
            return new ResponseEntity<>("Account has been confirmed", HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid confirmation code");
        }
    }


}
