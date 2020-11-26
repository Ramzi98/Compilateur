package edu.ubfc.st.vm.project.grp7.memory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuadrupletTest {
    Quadruplet quadrupletIntVar;
    Quadruplet quadrupletIntVarNoId;

    @Before
    public void init(){
        quadrupletIntVar = new Quadruplet("i",5,OBJ.VAR, SORTE.INT);
        quadrupletIntVarNoId = new Quadruplet(null,5,OBJ.VAR, SORTE.INT);
    }



    @Test
    public void idTest(){
        Assert.assertEquals(quadrupletIntVar.id(),"i");
    }

    @Test(expected = IllegalArgumentException.class)
    public void idTestWithNull(){

        quadrupletIntVar.id(null);
    }

    @Test
    public void idTestIdNull(){
        quadrupletIntVar=new Quadruplet(null,5,OBJ.VAR,SORTE.INT);
        quadrupletIntVar.id("i");
        Assert.assertEquals(quadrupletIntVar.id(),"i");
    }

    @Test(expected = IllegalStateException.class)
    public void idTestIdInsertNewIdent(){
        quadrupletIntVar.id("i");
        Assert.assertEquals(quadrupletIntVar.id(),"i");
    }

    @Test
    public void valTest(){
        Assert.assertEquals(quadrupletIntVar.val(),5);
    }

    @Test
    public void valInsertTest(){
        quadrupletIntVar.val(6);
        Assert.assertEquals(quadrupletIntVar.val(),6);
    }

    @Test
    public void natureTest(){
        Assert.assertEquals(quadrupletIntVar.nature(),OBJ.VAR);
    }

    @Test
    public void natureChangeTest(){
        quadrupletIntVar.nature(OBJ.CST);
        Assert.assertEquals(quadrupletIntVar.nature(),OBJ.CST);
    }


    @Test
    public void sorteTest(){
        Assert.assertEquals(quadrupletIntVar.type(),SORTE.INT);
    }

    @Test
    public void sorteChangeTest(){
        quadrupletIntVar.type(SORTE.BOOLEAN);
        Assert.assertEquals(quadrupletIntVar.type(),SORTE.BOOLEAN);
    }

    @Test
    public void toStringTest(){
        Assert.assertEquals(quadrupletIntVar.toString(), "<i, 5, var, int>");
    }


    /*



    @Override
    public String toString() {
        return String.format(
                "<%s, %s, %s, %s>",
                this.id == null ? "w" : this.id,
                this.val == null ? "w" : this.toString(),
                this.nature == null ? "w" : this.toString(),
                this.type == null ? "w" : this.toString()
        );
    }*/

}