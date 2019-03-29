package com.kunal.PlayGround;

import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.kunal.AllVariables;
import com.kunal.utils.BodyGenerator;

import java.util.LinkedList;

public class ObjectCreation {
    float ver[];

    PlayAreaUtils playAreaUtils;

    LinkedList<Byte> vertPoly;
    Boolean ismod4 = false, isHorizontal = false, ismod5 = false, ismod3 = false;


    public ObjectCreation() {
        playAreaUtils = new PlayAreaUtils();
        vertPoly = new LinkedList<Byte>();
        ismod4 = false;
    }


    public void CreateBicycle(World world, int verticalElevation){
        //Land surface and for now it is temerory
       // BodyGenerator.BodyAssemble(world, true, "Land", new Vector2(640, verticalElevation -100),
         //       new Vector2(12000, 50), 1,1, AllVariables.Bit_land,
           //     (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool|AllVariables.Bit_land));


        RevoluteJointDef rdef = new RevoluteJointDef();



        //--------------------Bicycle Parts
        AllVariables.BackWheel = BodyGenerator.CircleBody(world, false, "Bicycle", new Vector2(38,verticalElevation + 25),
                25,0.5f, 0.8f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_enimes | AllVariables.Bit_Tool| AllVariables.Bit_land));


        AllVariables.rod3 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(80, verticalElevation + 44),
                new Vector2(30,3),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land| AllVariables.Bit_Tool|AllVariables.Bit_enimes));

        //rod3 and back wheel
        rdef.bodyA = AllVariables.BackWheel;
        rdef.bodyB = AllVariables.rod3;
        rdef.localAnchorB.set(-27/AllVariables.PPM,0);
        world.createJoint(rdef);


        AllVariables.rod4 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(132, verticalElevation + 22),
                new Vector2(23,3),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land|AllVariables.Bit_enimes| AllVariables.Bit_Tool));

        //rod4 and back wheel
        rdef.bodyA = AllVariables.BackWheel;
        rdef.bodyB = AllVariables.rod4;
        rdef.localAnchorB.set(-23/AllVariables.PPM,0);
        world.createJoint(rdef);


        AllVariables.rod5 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(105, verticalElevation + 51),
                new Vector2(3,33),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land|AllVariables.Bit_enimes| AllVariables.Bit_Tool));

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


        AllVariables.rod1 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(109, verticalElevation + 63),
                new Vector2(25,3),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land|AllVariables.Bit_enimes| AllVariables.Bit_Tool));

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

        AllVariables.rod2 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(109, verticalElevation + 44),
                new Vector2(35,3),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land|AllVariables.Bit_enimes| AllVariables.Bit_Tool));

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

        AllVariables.rod6 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(158, verticalElevation + 56),
                new Vector2(3,35),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_land|AllVariables.Bit_enimes| AllVariables.Bit_Tool));



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


        AllVariables.FrontWheel = BodyGenerator.CircleBody(world, false, "Bicycle", new Vector2(160,verticalElevation + 25),
                25, 0.5f,0.8f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_enimes|AllVariables.Bit_land| AllVariables.Bit_Tool));

        //frontwheel and rod6
        rdef.bodyA = AllVariables.rod6;
        rdef.bodyB = AllVariables.FrontWheel;
        rdef.localAnchorA.set(0,-33/AllVariables.PPM);
        rdef.localAnchorB.set(0,0);
        world.createJoint(rdef);

        AllVariables.rod3 = BodyGenerator.BodyAssemble(world, false, "Bicycle", new Vector2(130, verticalElevation + 25),
                new Vector2(30,3),0.6f, 0.5f, AllVariables.Bit_Bicycle, (short)(AllVariables.Bit_enimes| AllVariables.Bit_Tool));

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


    //for cutoutBodies list
    //first filling with true cordinates so that to obtain a polygon
    //finaly putting them in body to create body of it
    public void CreateCutouts(World world){
        VariablesForPlayArea.CutOutBodies.clear();
        VariablesForPlayArea.CutoutShapeVertices.clear();
        /*createshapestoPolygonCompatible();

        //System.out.println();
        if (VariablesForPlayArea.CutoutShapeVertices.size() <= 8){
            for (int i = 0; i < VariablesForPlayArea.CutoutShapeVertices.size(); i++) {
                ver = new float[(VariablesForPlayArea.CutoutShapeVertices.get(i).size() * 2)];
                for (int j = 0, k = 0; j < VariablesForPlayArea.CutoutShapeVertices.get(i).size(); j++) {
                    ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.CutoutShapeVertices.get(i).get(0)][0] / (2) - VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.CutoutShapeVertices.get(i).get(j)][0] / (2);
                    k++;
                    ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.CutoutShapeVertices.get(i).get(0)][1] / (2) - VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.CutoutShapeVertices.get(i).get(j)][1] / (2);
                    k++;

                }

                Body b = BodyGenerator.PolyShape(world, false, "cutout", new Vector2(600, 80), ver, 1f, 0.2f, 0.5f, AllVariables.Bit_Tool, (short) (AllVariables.Bit_Bicycle));//| AllVariables.Bit_land | AllVariables.Bit_Tool));
                b.setTransform(VariablesForPlayArea.Sh_pos.get(i), (float) (VariablesForPlayArea.Angle_Of_Shape.get(i) * (Math.PI / 180)));
                VariablesForPlayArea.CutOutBodies.add(b);

                ver = null;
            }
        }else {*/
            for (int i = 0; i < VariablesForPlayArea.shapes.size(); i++) {
                ver = new float[(VariablesForPlayArea.shapes.get(i).size() * 2)];
                for (int j = 0, k = 0; j < VariablesForPlayArea.shapes.get(i).size(); j++) {
                    ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(0)][0] / (2) - VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][0] / (2);
                    k++;
                    ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(0)][1] / (2) - VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][1] / (2);
                    k++;
                }

                Body b = BodyGenerator.ChainLand(world, false, "cutout", new Vector2(600, 80), ver, 1f, 0.2f, 0.5f, AllVariables.Bit_Tool, (short) (AllVariables.Bit_Bicycle));//| AllVariables.Bit_land | AllVariables.Bit_Tool));
                b.setTransform(VariablesForPlayArea.Sh_pos.get(i), (float) (VariablesForPlayArea.Angle_Of_Shape.get(i) * (Math.PI / 180)));
                VariablesForPlayArea.CutOutBodies.add(b);


                ver = null;
            }
        //}

    }

    //try to minimize the shapes corrdinates to min of 8 to becaues in box2d more than 8 vertices the body cannot be init as polygon
    private void createshapestoPolygonCompatible(){

        for (int i =0; i<VariablesForPlayArea.shapes.size(); i++){
            vertPoly.add(VariablesForPlayArea.shapes.get(i).getFirst());
            if ((VariablesForPlayArea.shapes.get(i).getFirst() - VariablesForPlayArea.shapes.get(i).get(1))%4 == 0){
                ismod4 = true;
            }else{
                ismod4 = false;
            }

            //horijontal
            if ((VariablesForPlayArea.shapes.get(i).get(0) - VariablesForPlayArea.shapes.get(i).get(1)) == -1
                    || (VariablesForPlayArea.shapes.get(i).get(0) - VariablesForPlayArea.shapes.get(i).get(1)) == 1){
                if ((VariablesForPlayArea.shapes.get(i).get(0) == 3 && VariablesForPlayArea.shapes.get(i).get(1) == 4) ||
                        (VariablesForPlayArea.shapes.get(i).get(1) == 3 && VariablesForPlayArea.shapes.get(i).get(0) == 4) ||
                        (VariablesForPlayArea.shapes.get(i).get(0) == 7 && VariablesForPlayArea.shapes.get(i).get(1) == 8) ||
                        (VariablesForPlayArea.shapes.get(i).get(0) == 8 && VariablesForPlayArea.shapes.get(i).get(1) == 7) ||
                        (VariablesForPlayArea.shapes.get(i).get(0) == 11 && VariablesForPlayArea.shapes.get(i).get(1) == 12) ||
                        (VariablesForPlayArea.shapes.get(i).get(0) == 12 && VariablesForPlayArea.shapes.get(i).get(1) == 11)
                ){
                    isHorizontal = false;
                }else {
                    isHorizontal = true;
                }
            }else{
                isHorizontal = false;
            }

            //mod5
            if ((VariablesForPlayArea.shapes.get(i).getFirst() - VariablesForPlayArea.shapes.get(i).get(1))%5 == 0){
                ismod5 = true;
            }else{
                ismod5 = false;
            }

            //mod3
            if ((VariablesForPlayArea.shapes.get(i).getFirst() - VariablesForPlayArea.shapes.get(i).get(1))%3 == 0){
                ismod3 = true;
            }else{
                ismod3 = false;
            }


            for (int j=1; j<VariablesForPlayArea.shapes.get(i).size()-1; j++){
                //ismod4
                if ((VariablesForPlayArea.shapes.get(i).get(j) - VariablesForPlayArea.shapes.get(i).get(j + 1))%4 == 0){
                    if (!ismod4)
                        vertPoly.add(VariablesForPlayArea.shapes.get(i).get(j));
                    ismod4 = true;
                    isHorizontal = false;
                    ismod5 = false;
                    ismod3 = false;
                }
                //mod5
                else if ((VariablesForPlayArea.shapes.get(i).get(j) - VariablesForPlayArea.shapes.get(i).get(j+1))%5 == 0){
                    if (!ismod5)
                        vertPoly.add(VariablesForPlayArea.shapes.get(i).get(j));
                    ismod5 = true;
                    isHorizontal = false;
                    ismod4 = false;
                    ismod3 = false;
                }
                else if ((VariablesForPlayArea.shapes.get(i).get(j) - VariablesForPlayArea.shapes.get(i).get(j+1))%3 == 0){
                    if (!ismod3)
                        vertPoly.add(VariablesForPlayArea.shapes.get(i).get(j));
                    ismod3 = true;
                    isHorizontal = false;
                    ismod4 = false;
                    ismod5 = false;
                }
                //mod3

                //horijontal
                else if ((VariablesForPlayArea.shapes.get(i).get(j) - VariablesForPlayArea.shapes.get(i).get(j + 1)) == -1
                    || (VariablesForPlayArea.shapes.get(i).get(j) - VariablesForPlayArea.shapes.get(i).get(j + 1)) == 1){

                    if (!isHorizontal)
                        vertPoly.add(VariablesForPlayArea.shapes.get(i).get(j));

                        isHorizontal = true;
                        ismod4 = false;
                        ismod5 = false;
                        ismod3 = false;

                } else {
                    vertPoly.add(VariablesForPlayArea.shapes.get(i).get(j));
                    ismod4 = false;
                    isHorizontal = false;
                    ismod5 = false;
                    ismod3 = false;
                }

            }
            vertPoly.add(VariablesForPlayArea.shapes.get(i).getLast());
            VariablesForPlayArea.CutoutShapeVertices.add(vertPoly);
            vertPoly = new LinkedList<Byte>();
        }
    }


}
