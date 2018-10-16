/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uet.service;

import com.uet.model.PersonEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Storm Spirit
 */
public class PersonService {
    private FileUtil fileUtil;

    public PersonService(String fileName) throws IOException {
        fileUtil = new FileUtil(fileName);
    }

    public void save(List<PersonEntity> personEntity){
        try {
            List<String> data = new ArrayList<>();

            for(PersonEntity person: personEntity){
                data.add(person.toString());
            }
            fileUtil.writeData(data);
        } catch (Exception e){

        }
    }

    public List convertData() throws IOException {
        List<PersonEntity> personEntities = new ArrayList<>();

        List<String[]> dataFromFile = fileUtil.readData();
        for(String[] arr: dataFromFile){
            PersonEntity personEntity = new PersonEntity();
            personEntity.name = arr[0];
            personEntity.address = arr[1];
            personEntity.phone = arr[2];
            personEntity.createdAt = arr[3];
            personEntity.total = Integer.parseInt(arr[4]);

            try{
                personEntity.note = arr[5];
            } catch (Exception e){
                personEntity.note = "";
            }


            personEntities.add(personEntity);
        }

        return personEntities;
    }

    public Object[][] generateProviderObject() {
        List<PersonEntity> personList = new ArrayList<>();
        try {
            personList = convertData();
        } catch (Exception e){

        }

        Object[][] providerObj = new Object[personList.size()][7];

        for(int i = 0; i < personList.size(); i++){
            providerObj[i][0] = i + 1;
            providerObj[i][1] = personList.get(i).name;
            providerObj[i][2] = personList.get(i).address;
            providerObj[i][3] = personList.get(i).phone;
            providerObj[i][4] = personList.get(i).createdAt;
            providerObj[i][5] = personList.get(i).total;
            providerObj[i][6] = personList.get(i).note;
        }

        return providerObj;
    }
}
