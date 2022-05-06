package com.stock.hot.service;

public final class DbaSourceService {
    private static DbaSourceService INSTANCE;

    private DbaSourceService() {
    }

    public static DbaSourceService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DbaSourceService();
        }
        return INSTANCE;
    }



    public String getNoEstoqueProduto(){
        return "estoqueproduto";
    }
}
