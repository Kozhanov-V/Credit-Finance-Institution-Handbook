package com.kozhanov.creditFinanceInstitutionHandbook.controller;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;
import com.kozhanov.creditFinanceInstitutionHandbook.model.users.User;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.auth.UserRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.BICDirectoryEntryRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

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
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/api/favorites/add/{bic}")
    public ResponseEntity<?> addBicDirectoryEntry(@PathVariable int bic, @RequestBody String username) {
        username = username.substring(0,username.length()-1);
        System.out.println("BIC - "+bic+ " USERNAME - " + username);

        User user = userRepository.findByUsername(username);
        BICDirectoryEntry bicDirectoryEntry = bicDirectoryEntryRepository.findByBIC(bic).get();
        user.getBicDirectoryEntries().add(bicDirectoryEntry);
        userRepository.save(user);

        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }

    @PostMapping("/api/favorites/delete/{bic}")
    public ResponseEntity<?> deleteBicDirectoryEntry(@PathVariable int bic, @RequestBody String username) {
        username = username.substring(0,username.length()-1);
        System.out.println("BIC - "+bic+ " USERNAME - " + username);

        User user = userRepository.findByUsername(username);
        BICDirectoryEntry bicDirectoryEntry = bicDirectoryEntryRepository.findByBIC(bic).get();
        if(user.getBicDirectoryEntries().contains(bicDirectoryEntry)){
            user.getBicDirectoryEntries().remove(bicDirectoryEntry);
            userRepository.save(user);
        }


        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }


}
