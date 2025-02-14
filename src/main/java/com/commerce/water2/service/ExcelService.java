package com.commerce.water2.service;

import com.commerce.water2.model.Water2;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {
    private static final String FILE_PATH = "C:\\Users\\visha\\Documents\\Book3.xlsx";


    // Read data from Excel
    public List<Water2> readExcel() {
        List<Water2> waterList = new ArrayList<>();

        // Check if file exists before reading
        if (!Files.exists(Paths.get(FILE_PATH))) {
            System.out.println("Excel file not found: " + FILE_PATH);
            return waterList; // Return empty list
        }

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                System.out.println("Sheet is missing in the Excel file.");
                return waterList;
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                try {
                    Water2 water = new Water2();

                    // Reading and handling null cells safely
                    Cell idCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell nameCell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell addressCell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell phoneCell = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell infoCell = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    // Parse values safely
                    water.setId(idCell.getCellType() == CellType.NUMERIC ? (long) idCell.getNumericCellValue() : null);
                    water.setName(nameCell.getStringCellValue());
                    water.setAddress(addressCell.getStringCellValue());
                    water.setPhoneNo(phoneCell.getStringCellValue());
                    water.setAdditionalInfo(infoCell.getStringCellValue());

                    waterList.add(water);
                } catch (Exception e) {
                    System.out.println("Skipping invalid row: " + row.getRowNum() + " | Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
        }
        return waterList;
    }

    // Write data to Excel
    public void writeExcel(List<Water2> waterList) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {

            Sheet sheet = workbook.createSheet("Water2 Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Address");
            headerRow.createCell(3).setCellValue("Phone No");
            headerRow.createCell(4).setCellValue("Additional Info");

            // Write data rows
            int rowIdx = 1;
            for (Water2 water : waterList) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(water.getId() != null ? water.getId() : 0);
                row.createCell(1).setCellValue(water.getName() != null ? water.getName() : "");
                row.createCell(2).setCellValue(water.getAddress() != null ? water.getAddress() : "");
                row.createCell(3).setCellValue(water.getPhoneNo() != null ? water.getPhoneNo() : "");
                row.createCell(4).setCellValue(water.getAdditionalInfo() != null ? water.getAdditionalInfo() : "");
            }

            workbook.write(fileOut);
            System.out.println("Excel file successfully saved: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
        }
    }
}
