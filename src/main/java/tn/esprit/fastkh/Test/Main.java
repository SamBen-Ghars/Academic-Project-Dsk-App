package tn.esprit.fastkh.Test;

import tn.esprit.fastkh.Interfaces.InterfaceCRUD;
import tn.esprit.fastkh.Models.BonsPlans;
import tn.esprit.fastkh.Services.BonsPlansService;



public class Main {

    public static void main(String[] args) {


        //BonsPlans BP = new BonsPlans("Resteau el Ayari ", "le meilleur ayari ", "Ain zaghouan");
        BonsPlans BP2 = new BonsPlans(2 ,"Resteau el Ayari ", "le meilleur ayari ", "Ain zaghouan");
        InterfaceCRUD<BonsPlans> TST = new BonsPlansService();
        //TST.add(BP);





        TST.update(BP2);


        TST.delete(1);

        System.out.println(TST.getAll());




        //BonsPlans BP =new BonsPlans(BP.getId()=1);







    }
}
