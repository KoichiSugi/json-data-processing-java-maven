package com.app.main;

import com.app.user.Rows;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Main {

    public static final String ClientsRecordsPath = "C:\\Users\\61432\\Desktop\\second_assessment\\JsonDataProcessing\\src\\main\\resources\\ClientsRecords.json";

    public static void main(String[] args) {
        System.out.println("main here");

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        try {
            // Convert JSON string from file to Object by providing file
           // ArrayList<Rows> rowsArrayList = mapper.readValue(new File(ClientsRecordsPath), ArrayList.class);
            Rows test = mapper.readValue(new File(ClientsRecordsPath), Rows.class);

            //json array to array object00
            System.out.println("JSON array to Array objects...");
           // System.out.println(rowsArrayList.get(0));
            System.out.println(test.getRows().get(1).getUserId());
            //print a single row
            System.out.println(test.getRows().get(1).toString());
            System.out.println(test.getRows().size());
            //print all the rows
            for (int i =0; i<test.getRows().size(); i++){
                System.out.println(test.getRows().get(i).toString());
            }

            //System.out.println(test);//this will print everything

//            for (Rows rows : rowsArray) {
//                System.out.println(rows);
//            }


//            List<Users> UserList = mapper.readValue(new File(ClientsRecordsPath), ArrayList.class);
//            List<Row> rowList = mapper.readValue(new File(ClientsRecordsPath), ArrayList.class);
//            //exp
//            //Row[] myobjects = mapper.readValue(ClientsRecordsPath, Row[].class);
//            List<Row> myObjects = Arrays.asList(mapper.readValue(ClientsRecordsPath, Row[].class));
//
//
//            //System.out.println(rowList.size());
//            System.out.println(myObjects);

//            }

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
