package com.example.demojfx2;

public enum Genre {

    M,
    Mme,
    ;


    public String toString(){
        switch (this) {
            case M:
                return "M. ";
            case Mme:
                return "Mme ";
        }
        return null;
    }

}
