package com.example.corincoronacheckinowner.corinDomain.tech;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import com.example.corincoronacheckinowner.corinDomain.model.HistoryEntity;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class POI { // Excel

    public static void xlsWiter(Context context, ArrayList<HistoryEntity> historyEntities) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;

        String[] header = {"Year", "Month", "Date", "Hour", "Minute", "Name", "Age", "Gender"};
        for(int i=0; i<header.length; i++){
            cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }

        for(int i=0; i<historyEntities.size(); i++){
            row = sheet.createRow(i+1);
            HistoryEntity historyEntity = historyEntities.get(i);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(historyEntity.getInTime());
            cell = row.createCell(0);
            cell.setCellValue(calendar.get(Calendar.YEAR));
            cell = row.createCell(1);
            cell.setCellValue(calendar.get(Calendar.MONTH));
            cell = row.createCell(2);
            cell.setCellValue(calendar.get(Calendar.DATE));
            cell = row.createCell(3);
            cell.setCellValue(calendar.get(Calendar.HOUR_OF_DAY));
            cell = row.createCell(4);
            cell.setCellValue(calendar.get(Calendar.MINUTE));
            cell = row.createCell(5);
            cell.setCellValue(historyEntity.getName());
            cell = row.createCell(6);
            cell.setCellValue(historyEntity.getAge());
            cell = row.createCell(7);
            cell.setCellValue(historyEntity.getGender().ordinal());
        }

        try {
            File file = new File(context.getCacheDir(), "코로나 출입 명부.xls");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            workbook.close();
            fos.close();

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("application/excel");
            Uri contentUri = FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName() + ".fileprovider", file);
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            context.startActivity(Intent.createChooser(shareIntent,"파일 공유"));
        } catch (IOException e) { e.printStackTrace(); }
    }
}
