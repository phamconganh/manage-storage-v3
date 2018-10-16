/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uet.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Storm Spirit
 */
public class FileUtil {
    private BufferedWriter bw;
    private BufferedReader br;
    private String fileName;

    public FileUtil(String fileName) throws IOException {
        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }

        this.fileName = fileName;
    }

    public List<String[]> readData() throws IOException {
        br = new BufferedReader(new FileReader(new File(fileName)));

        List<String[]> data = new ArrayList<>();

        String inLine = "";
        while ((inLine = br.readLine()) != null) {
            data.add(inLine.split("-"));
        }

        br.close();
        return data;
    }

    public void writeData(List<String> data) throws IOException {
        bw = new BufferedWriter(new FileWriter(new File(fileName)));

        for(String inLine: data){
            bw.write(inLine + "\n");
        }
        bw.close();
    }


}
