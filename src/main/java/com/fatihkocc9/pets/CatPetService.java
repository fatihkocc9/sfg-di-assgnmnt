package com.fatihkocc9.pets;



/**
 * Created by jt on 12/28/19.
 */
//@Profile("cat")
//@Service("petService")
public class CatPetService implements PetService {

    @Override
    public String getPetType() {
        return "Cats Are the Best!";
    }
}
