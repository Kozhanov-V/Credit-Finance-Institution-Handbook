package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook.ElectronicDocumentsDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.ElectronicDocumentsRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ImportServiceImpl implements ImportService{

    @Autowired
    private ElectronicDocumentsRepository electronicDocumentsRepository;

    @Override
    public void importFromCB() {
        String url = "http://cbr.ru/s/newbik";
        String directory = "C:\\Users\\vkozh\\Documents\\GitHub" +
                "\\Credit-Finance-Institution-Handbook\\backend\\src\\main\\java\\com\\kozhanov\\creditFinanceInstitutionHandbook\\until\\";
       // String nameFile = "20230705_ED807_full.xml";
        String nameFile = "newbik.zip";

        try {
            downloadFile(url,directory+"\\"+nameFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

       nameFile= unzipFile(directory,nameFile);

        deserializeAndSaveEDFromXml(directory+nameFile);
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
        try {
            String readContent = new String(Files.readAllBytes(Paths.get(xmlFilePath)));
            ElectronicDocumentsDeserializer electronicDocumentsDeserializer = xmlMapper.readValue(readContent,ElectronicDocumentsDeserializer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

//
//        try {
//            String readContent = new String(Files.readAllBytes(Paths.get(xmlFilePath)));
//            ElectronicDocumentsDeserializer electronicDocuments = xmlMapper.readValue(readContent, ElectronicDocumentsDeserializer.class);
//            System.out.println(3142412);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
