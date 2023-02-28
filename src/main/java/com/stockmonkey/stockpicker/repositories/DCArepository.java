package com.stockmonkey.stockpicker.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.stockmonkey.stockpicker.models.DCAmodel;

@Repository
public class DCArepository {

    private List<DCAmodel> dcaList = new LinkedList<>();

    public boolean add(DCAmodel dcaModel){
        // if(dcaList.contains(dcaModel)){
        //     delete(dcaModel);
        // }
        System.out.println("Repository add model");
        return dcaList.add(dcaModel);
    }

    public boolean delete(DCAmodel dcaModel){
        return dcaList.remove(dcaModel);
    }

    public List<DCAmodel> getAll(){
        System.out.println("Repository get model list");
        return this.dcaList;
    
    }
    public List<DCAmodel> printAll(){
        for(DCAmodel i:this.dcaList){
            System.out.println(i.toString());
        }
        return this.dcaList;
    }

    
}
