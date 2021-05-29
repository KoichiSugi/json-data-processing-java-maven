package com.app.main;

import com.app.user.Rows;
import com.app.user.Users;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

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

            // Convert JSON string from file to Object
//            Rows row = mapper.readValue(new File(ClientsRecordsPath), Rows.class);

            //Collection<Users> UserList = mapper.readValue(new File(ClientsRecordsPath), Collection.class);
            List<Users> UserList = mapper.readValue(new File(ClientsRecordsPath), ArrayList.class);
            List<Users> UserList2 = mapper.readValue(new File(ClientsRecordsPath), new TypeReference<List<Users>>() {});
            //exp
//            ObjectMapper objectMapper = new ObjectMapper();
//            TypeFactory typeFactory = objectMapper.getTypeFactory();
//            List<Users> UserList = objectMapper.readValue(ClientsRecordsPath, typeFactory.constructCollectionType(List.class, Users.class));

            System.out.println(UserList2.size());

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
