package tn.esprit.fastkh.test;

import tn.esprit.fastkh.models.BonsPlans;
import tn.esprit.fastkh.services.BonsPlansService;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {
        BonsPlansService bonsPlansService = new BonsPlansService();
       // InterfaceCRUD<BonsPlans> bonsPlansService = new BonsPlansService();

        try {
            // Load image from file and convert to byte array
            byte[] imageBytes = loadImage("D:\\Telechargement/Home(1).png");

            // Create a BonsPlans object with attributes including the image
            BonsPlans bonPlan = new BonsPlans("Resteau el Ayari", "le meilleur ayari", "Ain zaghouan", imageBytes);

            // Add BonsPlans to database
            bonsPlansService.add(bonPlan);
            System.out.println("Bons plan with image added successfully.");



            // Retrieve all BonsPlans and print
        //    List<BonsPlans> allBonsPlans = bonsPlansService.getAll();
         //   for (BonsPlans bp : allBonsPlans) {
         //       System.out.println(bp);
         //   }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(bonsPlansService.getAll());
    }

    // Method to load image from file and convert to byte array
    private static byte[] loadImage(String imagePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(imagePath);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }
}


        //TST.update(new BonsPlans(5 ,"Resteau el Ayari ", "le meilleur ayari ", "Ain zaghouan"));

        //try {

        //TST.update(new BonsPlans(11 ,"Resteau lAYLA ", "le meilleur  ", "Ain zaghouan");)
        // } catch (SQLException e) {
        //    throw new RuntimeException(e);
        // }


        //BonsPlans BP =new BonsPlans(BP.getId()=1);


        //  BonsPlans P = new BonsPlans("Resteau el Ayari ", "le meilleur ayari ", "Ain zaghouan");
        //   BonsPlans P1 = new BonsPlans(, " le grand bleu ", "le meilleur ayari ", "Ain zaghouan");
        ///  BonsPlans P3 = new BonsPlans("hello3 ", "le meilleur ayari ", "Ain zaghouan");
        //   InterfaceCRUD<BonsPlans> TST = new BonsPlansService();

        //   TST.add(P3);


        //for (int i = 0; i < 50; i++) {
        //       TST.delete(i);
        //   }
        //    TST.add(P);
        //    TST.delete(15);
        //    TST.update(P1);
        //     System.out.println(TST.getAll());




