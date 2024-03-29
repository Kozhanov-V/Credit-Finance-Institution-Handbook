package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook.ElectronicDocumentsDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.ElectronicDocuments;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.AccountsRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@Transactional
public class ImportServiceImpl implements ImportService{

    @Autowired
    private ElectronicDocumentsService electronicDocumentsService;

    @Autowired
    private BICDirectoryEntryService bicDirectoryEntryService;

    @Autowired
    private AccountsRepository accountsRepository;


    @Override
    public void importFromCB() {
        System.out.println("satart");
        String url = "http://cbr.ru/s/newbik";
        String directory = "../until/";

        String nameFile = "newbik.zip";

        try {
            downloadFile(url,directory+nameFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

       nameFile= unzipFile(directory,nameFile);

        deserializeAndSaveEDFromXml(directory+nameFile);
        System.out.println("end");
    }

    private void downloadFile(String url, String directory) throws IOException {
        FileUtils.copyURLToFile(new URL(url), new File(directory));

    }
    private String unzipFile(String directory,String nameFile){
        String nameFileAfterUnzip = null;
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(directory+nameFile))) {
            ZipEntry zipEntry = zis.getNextEntry();
            nameFileAfterUnzip = zipEntry.getName();
            byte[] buffer = new byte[1024];
            while (zipEntry != null) {
                File newFile = new File(directory + zipEntry.getName());
                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
                zipEntry = zis.getNextEntry();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameFileAfterUnzip;
    }

    public void deserializeAndSaveEDFromXml(String xmlFilePath) {
        XmlMapper xmlMapper = new XmlMapper();
        try (InputStream inputStream = new FileInputStream(xmlFilePath);
             Reader reader = new InputStreamReader(inputStream, Charset.forName("windows-1251"))) {

            // Use reader to read the data
            char[] arr = new char[8 * 1024]; // 8K at a time
            StringBuilder buffer = new StringBuilder();
            int numCharsRead;
            while ((numCharsRead = reader.read(arr, 0, arr.length)) != -1) {
                buffer.append(arr, 0, numCharsRead);
            }
            String readContent = buffer.toString();

            ElectronicDocumentsDeserializer electronicDocumentsDeserializer = xmlMapper.readValue(readContent, ElectronicDocumentsDeserializer.class);
            electronicDocumentsService.save(electronicDocumentsDeserializer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
