package com.kunal.PlayGround;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.kunal.AllVariables;
import com.kunal.utils.BodyGenerator;

public class ObjectCreation {

    public ObjectCreation() {
    }

    public void CreateBicycle(World world){
        //Land surface and for now it is temerory
        BodyGenerator.BodyAssemble(world, true, "Land", new Vector2(640, -50),
                new Vector2(12000, 50), 1,1, AllVariables.Bit_land,
                (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool|AllVariables.Bit_land));

        float f[] = {560, 5,900,150, 900, 5};


        BodyGenerator.PolyShape(world, false, "fa", new Vector2(570, 5), f,1,0,1,AllVariables.Bit_land, (short)(AllVariables.Bit_Bicycle | AllVariables.Bit_land));

        RevoluteJointDef rdef = new RevoluteJointDef();



        //--------------------Bicycle Parts
        AllVariables.BackWheel = BodyGenerator.CircleBody(world, false, "Bicycle", new Vector2(538,25),
                25,0.5f, 0.8f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_enimes|AllVariables.Bit_land));


        AllVariables.rod3 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(580, 44),
                new Vector2(30,3),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land|AllVariables.Bit_enimes));

        //rod3 and back wheel
        rdef.bodyA = AllVariables.BackWheel;
        rdef.bodyB = AllVariables.rod3;
        rdef.localAnchorB.set(-27/AllVariables.PPM,0);
        world.createJoint(rdef);


        AllVariables.rod4 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(632, 22),
                new Vector2(23,3),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land|AllVariables.Bit_enimes));

        //rod4 and back wheel
        rdef.bodyA = AllVariables.BackWheel;
        rdef.bodyB = AllVariables.rod4;
        rdef.localAnchorB.set(-23/AllVariables.PPM,0);
        world.createJoint(rdef);


        AllVariables.rod5 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(605, 51),
                new Vector2(3,33),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land|AllVariables.Bit_enimes));

        //rod5 and rod3
        rdef.bodyA = AllVariables.rod3;
        rdef.bodyB = AllVariables.rod5;
        rdef.localAnchorA.set(25/AllVariables.PPM,0);
        rdef.localAnchorB.set(0, 10/AllVariables.PPM);
        world.createJoint(rdef);

        //rod4 and rod5
        rdef.bodyA = AllVariables.rod4;
        rdef.bodyB = AllVariables.rod5;
        rdef.localAnchorA.set(20/AllVariables.PPM,0);
        rdef.localAnchorB.set(0, -32/AllVariables.PPM);
        world.createJoint(rdef);


        AllVariables.rod1 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(609, 63),
                new Vector2(25,3),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land|AllVariables.Bit_enimes));

        //rod1 and rod5
        rdef.bodyA = AllVariables.rod1;
        rdef.bodyB = AllVariables.rod5;
        rdef.localAnchorA.set(-24/AllVariables.PPM,0);
        rdef.localAnchorB.set(0, 10/AllVariables.PPM);
        world.createJoint(rdef);

        //rod3 and rod1
        rdef.bodyA = AllVariables.rod3;
        rdef.bodyB = AllVariables.rod1;
        rdef.localAnchorA.set(25/AllVariables.PPM,0);
        rdef.localAnchorB.set(-24/AllVariables.PPM, 0);
        world.createJoint(rdef);

        AllVariables.rod2 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(609, 44),
                new Vector2(35,3),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land|AllVariables.Bit_enimes));

        //rod2 and rod5
        rdef.bodyA = AllVariables.rod2;
        rdef.bodyB = AllVariables.rod5;
        rdef.localAnchorA.set(-34/AllVariables.PPM,0);
        rdef.localAnchorB.set(0, -32/AllVariables.PPM);
        world.createJoint(rdef);

        //rod1 and rod2
        rdef.bodyA = AllVariables.rod1;
        rdef.bodyB = AllVariables.rod2;
        rdef.localAnchorA.set(24/AllVariables.PPM,0);
        rdef.localAnchorB.set(29/AllVariables.PPM, 0);
        world.createJoint(rdef);

        //rod4 and rod2
        rdef.bodyA = AllVariables.rod4;
        rdef.bodyB = AllVariables.rod2;
        rdef.localAnchorA.set(20/AllVariables.PPM,0);
        rdef.localAnchorB.set(-34/AllVariables.PPM, 0);
        world.createJoint(rdef);

        AllVariables.rod6 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(658, 56),
                new Vector2(3,35),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land|AllVariables.Bit_enimes));



        //rod6 and rod1
        rdef.bodyA = AllVariables.rod6;
        rdef.bodyB = AllVariables.rod1;
        rdef.localAnchorA.set(0,10/AllVariables.PPM);
        rdef.localAnchorB.set(24/AllVariables.PPM, 0);
        rdef.upperAngle = (float)(40*Math.PI/180);
        rdef.lowerAngle = (float)(40*Math.PI/180);
        world.createJoint(rdef);

        //rod6 and rod2
        rdef.bodyA = AllVariables.rod6;
        rdef.bodyB = AllVariables.rod2;
        rdef.localAnchorA.set(0,10/AllVariables.PPM);
        rdef.localAnchorB.set(29/AllVariables.PPM, 0);
        world.createJoint(rdef);


        AllVariables.FrontWheel = BodyGenerator.CircleBody(world, false, "Bicycle", new Vector2(660,25),
                25, 0.5f,0.8f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_enimes|AllVariables.Bit_land));

        //frontwheel and rod6
        rdef.bodyA = AllVariables.rod6;
        rdef.bodyB = AllVariables.FrontWheel;
        rdef.localAnchorA.set(0,-33/AllVariables.PPM);
        rdef.localAnchorB.set(0,0);
        world.createJoint(rdef);

        AllVariables.rod3 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(630, 25),
                new Vector2(30,3),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_enimes));

        //rod4 can be reused
        //joint b/w rod3 and frontwheel
        rdef.bodyA = AllVariables.FrontWheel;
        rdef.bodyB = AllVariables.rod3;
        rdef.localAnchorA.set(0,0);
        rdef.localAnchorB.set(30/AllVariables.PPM,0);
        world.createJoint(rdef);

        //rod3 reused and rod5
        rdef.bodyA = AllVariables.rod5;
        rdef.bodyB = AllVariables.rod3;
        rdef.localAnchorA.set(0,-32/AllVariables.PPM);
        rdef.localAnchorB.set(-27/AllVariables.PPM,0);
        world.createJoint(rdef);

        //rod3 reused and rod2
        rdef.bodyA = AllVariables.rod2;
        rdef.bodyB = AllVariables.rod3;
        rdef.localAnchorA.set(-34/AllVariables.PPM,0);
        rdef.localAnchorB.set(-27/AllVariables.PPM,0);
        world.createJoint(rdef);

        //rod3 reused and rod4
        rdef.bodyA = AllVariables.rod4;
        rdef.bodyB = AllVariables.rod3;
        rdef.localAnchorA.set(20/AllVariables.PPM,0);
        rdef.localAnchorB.set(-27/AllVariables.PPM,0);
        world.createJoint(rdef);





    }
    
    short len = 3;

    public void PlayerCreation(World world){
        AllVariables.Front_foot1 = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(600,50), new Vector2(len + len,len),.5f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));

        AllVariables.Back_foot1 = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(600,50), new Vector2(len + len,len),.5f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));

        AllVariables.Front_foot2 = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(608,50), new Vector2(len+len/2 ,len),0.5f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));

        AllVariables.Back_foot2 = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(608,50), new Vector2(len+len/2 ,len),0.5f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));

        AllVariables.Front_leg = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604,63), new Vector2(len,len*4f),0.8f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));

        AllVariables.Back_leg = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604,63), new Vector2(len,len*4f),0.8f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));


        AllVariables.Front_Thai = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604,80), new Vector2(len,len*5f),0.7f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));

        AllVariables.Back_Thai = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604,80), new Vector2(len,len*5f),0.7f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));

        AllVariables.Stomach1 = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604,90), new Vector2(len*1.5f,len*2.5f),0.5f,
                0.5f,(short) (AllVariables.Bit_Bicycle),
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land   | AllVariables.Bit_Tool));

        AllVariables.Stomach2 = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604,100), new Vector2(len*1.5f,len*2.5f),0.4f,
                0.5f,(short) (AllVariables.Bit_Bicycle),
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land  | AllVariables.Bit_Tool));

        AllVariables.Stomach3 = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604,110), new Vector2(len*2,len*3.5f),0.3f,
                0.5f,(short) (AllVariables.Bit_Bicycle),
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land  | AllVariables.Bit_Tool));

        AllVariables.Front_arm = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604, 100), new Vector2(len , len * 3.5f), 0.2f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));

        AllVariables.Back_arm = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604, 100), new Vector2(len , len * 3.5f), 0.2f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));

        AllVariables.Front_hand = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604, 100), new Vector2(len , len * 3.5f), 0.2f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));

        AllVariables.Back_hand = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604, 100), new Vector2(len , len * 3.5f), 0.2f,
                0.5f,AllVariables.Bit_Bicycle,
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));



        AllVariables.neck = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604,120), new Vector2(len,len*2.5f),0.3f,
                0.5f,(short) (AllVariables.Bit_Bicycle),
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));

        AllVariables.head = BodyGenerator.BodyAssemble(world, false, "player", new Vector2(604,130), new Vector2(len*3f,len*3.5f),0.3f,
                0.5f,(short) (AllVariables.Bit_Bicycle),
                (short)(AllVariables.Bit_enimes | AllVariables.Bit_land | AllVariables.Bit_Tool));




        RevoluteJointDef rdef = new RevoluteJointDef();


        rdef.bodyA = AllVariables.Front_foot1;
        rdef.bodyB = AllVariables.Front_leg;
        rdef.collideConnected = false;
        rdef.localAnchorA.set(-((len-(len/2))/AllVariables.PPM),0);
        rdef.localAnchorB.set(0,-((len*5 - (len/2))/AllVariables.PPM));
        rdef.lowerAngle = (float) (-0.2f * Math.PI);
        rdef.upperAngle = (float) (0.07f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = AllVariables.Front_foot2;
        rdef.localAnchorA.set((len + len/2)/AllVariables.PPM,0);
        rdef.localAnchorB.set(-((len+len/2)/AllVariables.PPM),0);
        rdef.lowerAngle = 0;
        rdef.upperAngle = (float) (0.3f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = AllVariables.Front_leg;
        rdef.bodyA = AllVariables.Front_Thai;
        rdef.localAnchorA.set(0,-(len*5 - (len/2))/AllVariables.PPM);
        rdef.localAnchorB.set(0,((len*4 - (len/2))/AllVariables.PPM));
        rdef.upperAngle = 0;
        rdef.lowerAngle = -(float) (0.7f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = AllVariables.Stomach1;
        rdef.localAnchorA.set(0,(len*5 - (len/2))/AllVariables.PPM);
        rdef.localAnchorB.set(0,-(len*2)/AllVariables.PPM);
        rdef.upperAngle = (float) (0.04f * Math.PI);
        rdef.lowerAngle = (float) (-0.5f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyA = AllVariables.Stomach2;
        rdef.localAnchorA.set(0, -(len*2)/AllVariables.PPM);
        rdef.localAnchorB.set(0 , (len*2)/AllVariables.PPM);
        rdef.upperAngle = (float) (0.1f * Math.PI);
        rdef.lowerAngle = -(float) (0.05f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyB = AllVariables.Stomach3;
        rdef.localAnchorA.set( 0, (len*2)/AllVariables.PPM);
        rdef.localAnchorB.set(-(len/2) /AllVariables.PPM, -(len*3)/AllVariables.PPM);
        rdef.upperAngle = 0;
        rdef.lowerAngle = -(float) (0.05f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyA = AllVariables.Front_arm;
        rdef.localAnchorA.set(0 , (len*3)/AllVariables.PPM);
        rdef.localAnchorB.set(-(len/2)/AllVariables.PPM , (len*3)/AllVariables.PPM);
        rdef.upperAngle = (float) (0.5f * Math.PI);
        rdef.lowerAngle = (float) (-1f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyB = AllVariables.Front_hand;
        rdef.localAnchorA.set(0 , -(len*3)/AllVariables.PPM);
        rdef.localAnchorB.set(0 , (len*3)/AllVariables.PPM);
        rdef.upperAngle = (float) (0.4f * Math.PI);
        rdef.lowerAngle = 0;

        world.createJoint(rdef);

        rdef.bodyB = AllVariables.Stomach3;
        rdef.bodyA = AllVariables.Back_arm;
        rdef.localAnchorA.set(0 , (len*3)/AllVariables.PPM);
        rdef.localAnchorB.set(-(len/2)/AllVariables.PPM , (len*3)/AllVariables.PPM);
        rdef.upperAngle = (float) (0.5f * Math.PI);
        rdef.lowerAngle = (float) (-1f * Math.PI);

        world.createJoint(rdef);


        rdef.bodyB = AllVariables.Back_hand;
        rdef.localAnchorA.set(0 , -(len*3)/AllVariables.PPM);
        rdef.localAnchorB.set(0 , (len*3)/AllVariables.PPM);
        rdef.upperAngle = (float) (0.4f * Math.PI);
        rdef.lowerAngle = 0;

        world.createJoint(rdef);




        rdef.bodyB = AllVariables.Stomach3;
        rdef.bodyA = AllVariables.neck;
        rdef.localAnchorA.set(0 , -(len*2)/AllVariables.PPM);
        rdef.localAnchorB.set(-(len/2)/AllVariables.PPM , (len*3)/AllVariables.PPM);
        rdef.upperAngle = (float) (0.05f * Math.PI);
        rdef.lowerAngle = (float) (-0.05f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyB = AllVariables.head;
        rdef.localAnchorA.set(0 , (len*2)/AllVariables.PPM);
        rdef.localAnchorB.set(-(len/2)/AllVariables.PPM , -(len*2.5f)/AllVariables.PPM);
        rdef.upperAngle = (float) (0.1f * Math.PI);
        rdef.lowerAngle = (float) (-0.1f * Math.PI);

        world.createJoint(rdef);


        //bqck joiminh---------------------------------------------------------------------------------
        rdef.bodyA = AllVariables.Back_foot1;
        rdef.bodyB = AllVariables.Back_leg;
        rdef.localAnchorA.set(-((len-(len/2))/AllVariables.PPM),0);
        rdef.localAnchorB.set(0,-((len*5 - (len/2))/AllVariables.PPM));
        rdef.lowerAngle = (float) (-0.2f * Math.PI);
        rdef.upperAngle = (float) (0.07f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = AllVariables.Back_foot2;
        rdef.localAnchorA.set((len + len/2)/AllVariables.PPM,0);
        rdef.localAnchorB.set(-((len+len/2)/AllVariables.PPM),0);
        rdef.lowerAngle = 0;
        rdef.upperAngle = (float) (0.3f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = AllVariables.Back_leg;
        rdef.bodyA = AllVariables.Back_Thai;
        rdef.localAnchorA.set(0,-(len*5 - (len/2))/AllVariables.PPM);
        rdef.localAnchorB.set(0,((len*4 - (len/2))/AllVariables.PPM));
        rdef.upperAngle = 0;
        rdef.lowerAngle = -(float) (0.7f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = AllVariables.Stomach1;
        rdef.localAnchorA.set(0,(len*5 - (len/2))/AllVariables.PPM);
        rdef.localAnchorB.set(0,-(len*2)/AllVariables.PPM);
        rdef.upperAngle = (float) (0.04f * Math.PI);
        rdef.lowerAngle = (float) (-0.5f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);


        //rod5 and stomach 1
        rdef.bodyA = AllVariables.rod5;
        rdef.bodyB = AllVariables.Stomach1;
        rdef.localAnchorA.set(0,(33f)/AllVariables.PPM);

        rdef.enableLimit = true;

        world.createJoint(rdef);



    }

}
