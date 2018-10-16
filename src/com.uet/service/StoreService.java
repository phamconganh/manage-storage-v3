package com.uet.service;

import com.uet.model.StoreEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreService {
  private FileUtil fileUtil;

  public StoreService(String fileName) throws IOException {
    fileUtil = new FileUtil(fileName);
  }

  public void save(List<StoreEntity> storeEntities){
    try {
      List<String> data = new ArrayList<>();

      for(StoreEntity storeEntity: storeEntities){
        data.add(storeEntity.toString());
      }
      fileUtil.writeData(data);
    } catch (Exception e){}
  }

  public List convertData() throws IOException {
    List<StoreEntity> storeEntities = new ArrayList<>();

    List<String[]> dataFromFile = fileUtil.readData();
    for(String[] arr: dataFromFile){
      StoreEntity storeEntity = new StoreEntity();
      storeEntity.name = arr[0];
      storeEntity.code = arr[1];
      storeEntity.person = arr[2];
      storeEntity.createdAt = arr[3];
      storeEntity.type = arr[4];
      storeEntity.quantity = Integer.parseInt(arr[5]);
      storeEntity.price = Integer.parseInt(arr[6]);
      storeEntity.total = Integer.parseInt(arr[7]);

      try{
        storeEntity.note = arr[7];
      } catch (Exception e){
        storeEntity.note = "";
      }


      storeEntities.add(storeEntity);
    }

    return storeEntities;
  }

  public Object[][] generateStoreObject(){
    List<StoreEntity> storeEntities = new ArrayList<>();

    try{
      storeEntities = convertData();
    } catch (Exception e){}

    Object[][] storeObj = new Object[storeEntities.size()][9];
    for(int i = 0; i < storeEntities.size(); i++){
      storeObj[i][0] = i + 1;
      storeObj[i][1] = storeEntities.get(i).name;
      storeObj[i][2] = storeEntities.get(i).code;
      storeObj[i][3] = storeEntities.get(i).person;
      storeObj[i][4] = storeEntities.get(i).createdAt;
      storeObj[i][5] = storeEntities.get(i).type;
      storeObj[i][6] = storeEntities.get(i).quantity;
      storeObj[i][7] = storeEntities.get(i).price;
      storeObj[i][8] = storeEntities.get(i).total;

    }

    return storeObj;
  }

}
