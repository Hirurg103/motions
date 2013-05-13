package figures;

import gui.dimensions.MotionDimension;
import gui.dimensions.builders.RotateDimensionBuilder;
import gui.dimensions.builders.StretchDimensionBuilder;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

import javax.media.opengl.GL2;

import javax.vecmath.Color4b;



import static javax.media.opengl.GL.GL_TEXTURE_2D;
import static javax.media.opengl.GL2.*;
import static javax.media.opengl.GL2ES1.GL_FOG;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;

public class HumanSkeleton {
	private SkeletonPart root;
	public static HashMap<Object, SkeletonPart> skeletonParts = new HashMap<Object, SkeletonPart>();
	public static HashMap<Object, MotionDimension> motionDimensions = new HashMap<Object, MotionDimension>();
	
	public HumanSkeleton() {
		RotateDimensionBuilder rotateDimensionBuilder = new RotateDimensionBuilder();
		StretchDimensionBuilder stretchDimensionBuilder = new StretchDimensionBuilder();
		
		//TODO: skeleton parts will load from database automatically
		SkeletonPart pelvis = new SkeletonPart("Pelvis", 0.3f)
				.setBone(new Bone(0.3f, 0.05f, -0.15f).setLeftCapsuleRadius(0.05f).setRightCapsuleRadius(0.05f))
				.setRotY(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", 180, -180, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -180, 180, 0))
				.setRotX(rotateDimensionBuilder.build("Slope:Back-Forward", -180, 180, 0))
				.setColor(0.9f, 0.8f, 0.8f);
		this.root = pelvis; skeletonParts.put(pelvis.getId(), pelvis);
		
		// ---------------------------------------- Spine ------------------------------------------

		SkeletonPart vertebra1th = new SkeletonPart("1th vertebra", 0.05f)
				.setPosition(new SkeletonPartPosition(0, 0.05f, 0).setRotZ(90))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		pelvis.addChildPart(vertebra1th); skeletonParts.put(vertebra1th.getId(), vertebra1th);
		
		SkeletonPart vertebra2th = new SkeletonPart("2th vertebra", 0.05f)
				.setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.7f);
		vertebra1th.addChildPart(vertebra2th); skeletonParts.put(vertebra2th.getId(), vertebra2th);
		
		SkeletonPart vertebra3th = new SkeletonPart("3th vertebra", 0.05f)
				.setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra2th.addChildPart(vertebra3th); skeletonParts.put(vertebra3th.getId(), vertebra3th);
		
		SkeletonPart vertebra4th = new SkeletonPart("4th vertebra", 0.05f)
				.setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.7f);
		vertebra3th.addChildPart(vertebra4th); skeletonParts.put(vertebra4th.getId(), vertebra4th);
		
		SkeletonPart vertebra5th = new SkeletonPart("5th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra4th.addChildPart(vertebra5th); skeletonParts.put(vertebra5th.getId(), vertebra5th);
		
		SkeletonPart vertebra6th = new SkeletonPart("6th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.7f);
		vertebra5th.addChildPart(vertebra6th); skeletonParts.put(vertebra6th.getId(), vertebra6th);
		
		SkeletonPart vertebra7th = new SkeletonPart("7th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra6th.addChildPart(vertebra7th); skeletonParts.put(vertebra7th.getId(), vertebra7th);
		
		SkeletonPart vertebra8th = new SkeletonPart("8th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.7f);
		vertebra7th.addChildPart(vertebra8th); skeletonParts.put(vertebra8th.getId(), vertebra8th);
		
		SkeletonPart vertebra9th = new SkeletonPart("9th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra8th.addChildPart(vertebra9th); skeletonParts.put(vertebra9th.getId(), vertebra9th);
		
		SkeletonPart vertebra10th = new SkeletonPart("10th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.7f);
		vertebra9th.addChildPart(vertebra10th); skeletonParts.put(vertebra10th.getId(), vertebra10th);

		SkeletonPart vertebra11th = new SkeletonPart("11th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra10th.addChildPart(vertebra11th); skeletonParts.put(vertebra11th.getId(), vertebra11th);
		
		SkeletonPart vertebra12th = new SkeletonPart("12th vertebra", 0.04f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.04f, 0.028f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.8f);
		vertebra11th.addChildPart(vertebra12th); skeletonParts.put(vertebra12th.getId(), vertebra12th);
		
		SkeletonPart vertebra13th = new SkeletonPart("13th vertebra", 0.04f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.04f, 0.028f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra12th.addChildPart(vertebra13th); skeletonParts.put(vertebra13th.getId(), vertebra13th);
		
		SkeletonPart vertebra14th = new SkeletonPart("14th vertebra", 0.04f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.04f, 0.028f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.8f);
		vertebra13th.addChildPart(vertebra14th); skeletonParts.put(vertebra14th.getId(), vertebra14th);
		
		SkeletonPart vertebra15th = new SkeletonPart("15th vertebra", 0.04f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.04f, 0.028f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra14th.addChildPart(vertebra15th); skeletonParts.put(vertebra15th.getId(), vertebra15th);
		
		SkeletonPart vertebra16th = new SkeletonPart("16th vertebra", 0.04f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.04f, 0.028f, 0))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -10, 10, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(stretchDimensionBuilder.build("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.8f);
		vertebra15th.addChildPart(vertebra16th); skeletonParts.put(vertebra16th.getId(), vertebra16th);
		
		// ---------------------------------------- Head ------------------------------------------
		
		SkeletonPart head = new SkeletonPart("Head", 0.12f).setPosition(new SkeletonPartPosition(1.0f, 0, 0.03f))
				.setBone(new Bone(0.12f, 0.07f, 0).setRightCapsuleRadius(0.06f))
				.setRotY(rotateDimensionBuilder.build("Slope:Back-Forward", 30, -30, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", -30, 30, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -30, 30, 0))
				.setColor(0.7f, 0.9f, 1.0f);
		vertebra16th.addChildPart(head); skeletonParts.put(head.getId(), head);
		
		// ------------------------------------- Right hand ---------------------------------------
		
		SkeletonPart rightCollarbone = new SkeletonPart("Right collarbone", 0.22f).setPosition(new SkeletonPartPosition(1.0f, 0.05f, 0).setRotZ(90))
				.setBone(new Bone(0.22f, 0.03f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(rotateDimensionBuilder.build("Rotation:Back-Forward", 30, -60, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", 10, -45, 0))
				.setColor(0.6f, 0.6f, 1.0f);
		vertebra11th.addChildPart(rightCollarbone); skeletonParts.put(rightCollarbone.getId(), rightCollarbone);
		
		SkeletonPart rightShoulder = new SkeletonPart("Right shoulder", 0.22f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.22f, 0.03f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", -150, 30, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", 90, -90, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -90, 90, 0))
				.setColor(0.8f, 0.8f, 0.2f);
				
		rightCollarbone.addChildPart(rightShoulder); skeletonParts.put(rightShoulder.getId(), rightShoulder);
		
		SkeletonPart rightElbow = new SkeletonPart("Right elbow", 0.2f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.2f, 0.028f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -150, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -150, 60, 0))
				.setColor(0.7f, 0.7f, 0.3f);
		rightShoulder.addChildPart(rightElbow); skeletonParts.put(rightElbow.getId(), rightElbow);
		
		SkeletonPart rightHand = new SkeletonPart("Right hand", 0.1f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.1f, 0.03f, 0).setR4(0.06f))
				.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", -90, 60, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", 45, -45, 0))
				.setColor(0.6f, 0.6f, 0.1f);
		rightElbow.addChildPart(rightHand); skeletonParts.put(rightHand.getId(), rightHand);
		
			// ------------------------------------- Thumb of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfThumbOfRightHand = new SkeletonPart("1th phalanx of the thumb of the right hand", 0.05f).setPosition(new SkeletonPartPosition(0.1f, -0.04f, 0).setRotZ(-45))
					.setBone(new Bone(0.05f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", -45, 10, 0))
					.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", 45, -15, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			rightHand.addChildPart(phalanx1thOfThumbOfRightHand); skeletonParts.put(phalanx1thOfThumbOfRightHand.getId(), phalanx1thOfThumbOfRightHand);
			
			SkeletonPart phalanx2thOfThumbOfRightHand = new SkeletonPart("2th phalanx of the thumb of the right hand", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0, 0).setRotZ(45).setRotX(-45))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -45, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfThumbOfRightHand.addChildPart(phalanx2thOfThumbOfRightHand); skeletonParts.put(phalanx2thOfThumbOfRightHand.getId(), phalanx2thOfThumbOfRightHand);
			
			SkeletonPart phalanx3thOfThumbOfRightHand = new SkeletonPart("3th phalanx of the thumb of the right hand", 0.03f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.03f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfThumbOfRightHand.addChildPart(phalanx3thOfThumbOfRightHand); skeletonParts.put(phalanx3thOfThumbOfRightHand.getId(), phalanx3thOfThumbOfRightHand);
			
			// ------------------------------------- Forefinger of the right hand ---------------------------------------
		
			SkeletonPart phalanx1thOfForefingerOfRightHand = new SkeletonPart("1th phalanx of the forefinger of the right hand", 0.045f).setPosition(new SkeletonPartPosition(1.1f, -0.045f, 0))
					.setBone(new Bone(0.045f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", -90, 45, 0))
					.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			rightHand.addChildPart(phalanx1thOfForefingerOfRightHand); skeletonParts.put(phalanx1thOfForefingerOfRightHand.getId(), phalanx1thOfForefingerOfRightHand);
			
			SkeletonPart phalanx2thOfForefingerOfRightHand = new SkeletonPart("2th phalanx of the forefinger of the right hand", 0.03f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.03f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfForefingerOfRightHand.addChildPart(phalanx2thOfForefingerOfRightHand); skeletonParts.put(phalanx2thOfForefingerOfRightHand.getId(), phalanx2thOfForefingerOfRightHand);
			
			SkeletonPart phalanx3thOfForefingerOfRightHand = new SkeletonPart("3th phalanx of the forefinger of the right hand", 0.022f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.022f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfForefingerOfRightHand.addChildPart(phalanx3thOfForefingerOfRightHand); skeletonParts.put(phalanx3thOfForefingerOfRightHand, phalanx3thOfForefingerOfRightHand);
			
			// ------------------------------------- Middle finger of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfMiddleFingerOfRightHand = new SkeletonPart("1th phalanx of the middle finger of the right hand", 0.05f).setPosition(new SkeletonPartPosition(1.1f, -0.01f, 0))
					.setBone(new Bone(0.05f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", -90, 45, 0))
					.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			rightHand.addChildPart(phalanx1thOfMiddleFingerOfRightHand); skeletonParts.put(phalanx1thOfMiddleFingerOfRightHand.getId(), phalanx1thOfMiddleFingerOfRightHand);
			
			SkeletonPart phalanx2thOfMiddleFingerOfRightHand = new SkeletonPart("2th phalanx of the middle finger of the right hand", 0.033f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.033f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfMiddleFingerOfRightHand.addChildPart(phalanx2thOfMiddleFingerOfRightHand); skeletonParts.put(phalanx2thOfMiddleFingerOfRightHand.getId(), phalanx2thOfMiddleFingerOfRightHand);
			
			SkeletonPart phalanx3thOfMiddleFingerOfRightHand = new SkeletonPart("3th phalanx of the middle finger of the right hand", 0.023f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.023f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfMiddleFingerOfRightHand.addChildPart(phalanx3thOfMiddleFingerOfRightHand); skeletonParts.put(phalanx3thOfMiddleFingerOfRightHand, phalanx3thOfMiddleFingerOfRightHand);
			
			// ------------------------------------- Annular of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfAnnularOfRightHand = new SkeletonPart("1th phalanx of the annular of the right hand", 0.044f).setPosition(new SkeletonPartPosition(1.1f, 0.025f, 0))
					.setBone(new Bone(0.044f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", -90, 45, 0))
					.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			rightHand.addChildPart(phalanx1thOfAnnularOfRightHand); skeletonParts.put(phalanx1thOfAnnularOfRightHand.getId(), phalanx1thOfAnnularOfRightHand);
			
			SkeletonPart phalanx2thOfAnnularOfRightHand = new SkeletonPart("2th phalanx of the annular of the right hand", 0.033f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.033f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfAnnularOfRightHand.addChildPart(phalanx2thOfAnnularOfRightHand); skeletonParts.put(phalanx2thOfAnnularOfRightHand.getId(), phalanx2thOfAnnularOfRightHand);
			
			SkeletonPart phalanx3thOfAnnularOfRightHand = new SkeletonPart("3th phalanx of the annular of the right hand", 0.022f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.022f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfAnnularOfRightHand.addChildPart(phalanx3thOfAnnularOfRightHand); skeletonParts.put(phalanx3thOfAnnularOfRightHand.getId(), phalanx3thOfAnnularOfRightHand);
			
			// ------------------------------------- Pinky of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfPinkyOfRightHand = new SkeletonPart("1th phalanx of the pinky of the right hand", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0.055f, 0))
					.setBone(new Bone(0.035f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", -90, 45, 0))
					.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			rightHand.addChildPart(phalanx1thOfPinkyOfRightHand); skeletonParts.put(phalanx1thOfPinkyOfRightHand.getId(), phalanx1thOfPinkyOfRightHand);
			
			SkeletonPart phalanx2thOfPinkyOfRightHand = new SkeletonPart("2th phalanx of the pinky of the right hand", 0.024f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.024f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfPinkyOfRightHand.addChildPart(phalanx2thOfPinkyOfRightHand); skeletonParts.put(phalanx2thOfPinkyOfRightHand.getId(), phalanx2thOfPinkyOfRightHand);
			
			SkeletonPart phalanx3thOfPinkyOfRightHand = new SkeletonPart("3th phalanx of the pinky of the right hand", 0.021f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.021f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfPinkyOfRightHand.addChildPart(phalanx3thOfPinkyOfRightHand); skeletonParts.put(phalanx3thOfPinkyOfRightHand.getId(), phalanx3thOfPinkyOfRightHand);
			

		// ------------------------------------- Left hand ---------------------------------------
		
		SkeletonPart leftCollarbone = new SkeletonPart("Left collarbone", 0.22f).setPosition(new SkeletonPartPosition(1.0f, -0.05f, 0).setRotZ(-90))
				.setBone(new Bone(0.22f, 0.03f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(rotateDimensionBuilder.build("Rotation:Back-Forward", 30, -60, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", -10, 45, 0))
				.setColor(0.6f, 0.6f, 1.0f);
		vertebra11th.addChildPart(leftCollarbone); skeletonParts.put(leftCollarbone.getId(), leftCollarbone);
		
		SkeletonPart leftShoulder = new SkeletonPart("Left shoulder", 0.22f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.22f, 0.03f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", 30, -150, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", -90, 90, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -90, 90, 0))
				.setColor(0.8f, 0.8f, 0.2f);
		leftCollarbone.addChildPart(leftShoulder); skeletonParts.put(leftShoulder.getId(), leftShoulder);
		
		SkeletonPart leftElbow = new SkeletonPart("Left elbow", 0.2f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.2f, 0.028f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -150, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclockwise-Clockwise", -150, 60, 0))
				.setColor(0.7f, 0.7f, 0.3f);
		leftShoulder.addChildPart(leftElbow); skeletonParts.put(leftElbow.getId(), leftElbow);
		
		SkeletonPart leftHand = new SkeletonPart("Left hand", 0.1f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.1f, 0.03f, 0).setR4(0.06f))
				.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", 60, -90, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", -45, 45, 0))
				.setColor(0.6f, 0.6f, 0.1f);
		leftElbow.addChildPart(leftHand); skeletonParts.put(leftHand.getId(), leftHand);
			
			// ------------------------------------- Thumb of the left hand ---------------------------------------
			
			SkeletonPart phalanx1thOfThumbOfLeftHand = new SkeletonPart("1th phalanx of the thumb of the left hand", 0.05f).setPosition(new SkeletonPartPosition(0.1f, 0.04f, 0).setRotZ(45))
					.setBone(new Bone(0.05f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", 10, -45, 0))
					.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", -15, 45, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			leftHand.addChildPart(phalanx1thOfThumbOfLeftHand); skeletonParts.put(phalanx1thOfThumbOfLeftHand.getId(), phalanx1thOfThumbOfLeftHand);
			
			SkeletonPart phalanx2thOfThumbOfLeftHand = new SkeletonPart("2th phalanx of the thumb of the left hand", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0, 0).setRotZ(-45).setRotX(45))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -45, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfThumbOfLeftHand.addChildPart(phalanx2thOfThumbOfLeftHand); skeletonParts.put(phalanx2thOfThumbOfLeftHand.getId(), phalanx2thOfThumbOfLeftHand);
			
			SkeletonPart phalanx3thOfThumbOfLeftHand = new SkeletonPart("3th phalanx of the thumb of the left hand", 0.03f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.03f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfThumbOfLeftHand.addChildPart(phalanx3thOfThumbOfLeftHand); skeletonParts.put(phalanx3thOfThumbOfLeftHand.getId(), phalanx3thOfThumbOfLeftHand);
			
			// ------------------------------------- Forefinger of the left hand ---------------------------------------
		
			SkeletonPart phalanx1thOfForefingerOfLeftHand = new SkeletonPart("1th phalanx of the forefinger of the left hand", 0.045f).setPosition(new SkeletonPartPosition(1.1f, 0.045f, 0))
					.setBone(new Bone(0.045f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", 45, -90, 0))
					.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", -30, 30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			leftHand.addChildPart(phalanx1thOfForefingerOfLeftHand); skeletonParts.put(phalanx1thOfForefingerOfLeftHand.getId(), phalanx1thOfForefingerOfLeftHand);
			
			SkeletonPart phalanx2thOfForefingerOfLeftHand = new SkeletonPart("2th phalanx of the forefinger of the left hand", 0.03f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.03f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfForefingerOfLeftHand.addChildPart(phalanx2thOfForefingerOfLeftHand); skeletonParts.put(phalanx2thOfForefingerOfLeftHand, phalanx2thOfForefingerOfLeftHand);
			
			SkeletonPart phalanx3thOfForefingerOfLeftHand = new SkeletonPart("3th phalanx of the forefinger of the left hand", 0.022f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.022f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfForefingerOfLeftHand.addChildPart(phalanx3thOfForefingerOfLeftHand); skeletonParts.put(phalanx3thOfForefingerOfLeftHand.getId(), phalanx3thOfForefingerOfLeftHand);
			
			// ------------------------------------- Middle finger of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfMiddleFingerOfLeftHand = new SkeletonPart("1th phalanx of the middle finger of the left hand", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0.01f, 0))
					.setBone(new Bone(0.05f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", 45, -90, 0))
					.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			leftHand.addChildPart(phalanx1thOfMiddleFingerOfLeftHand); skeletonParts.put(phalanx1thOfMiddleFingerOfLeftHand.getId(), phalanx1thOfMiddleFingerOfLeftHand);
			
			SkeletonPart phalanx2thOfMiddleFingerOfLeftHand = new SkeletonPart("2th phalanx of the middle finger of the left hand", 0.033f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.033f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfMiddleFingerOfLeftHand.addChildPart(phalanx2thOfMiddleFingerOfLeftHand); skeletonParts.put(phalanx2thOfMiddleFingerOfLeftHand.getId(), phalanx2thOfMiddleFingerOfLeftHand);
			
			SkeletonPart phalanx3thOfMiddleFingerOfLeftHand = new SkeletonPart("3th phalanx of the middle finger of the left hand", 0.023f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.023f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfMiddleFingerOfLeftHand.addChildPart(phalanx3thOfMiddleFingerOfLeftHand); skeletonParts.put(phalanx3thOfMiddleFingerOfLeftHand.getId(), phalanx3thOfMiddleFingerOfLeftHand);
			
			// ------------------------------------- Annular of the left hand ---------------------------------------
			
			SkeletonPart phalanx1thOfAnnularOfLeftHand = new SkeletonPart("1th phalanx of the annular of the left hand", 0.044f).setPosition(new SkeletonPartPosition(1.1f, -0.025f, 0))
					.setBone(new Bone(0.044f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", 45, -90, 0))
					.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			leftHand.addChildPart(phalanx1thOfAnnularOfLeftHand); skeletonParts.put(phalanx1thOfAnnularOfLeftHand.getId(), phalanx1thOfAnnularOfLeftHand);
			
			SkeletonPart phalanx2thOfAnnularOfLeftHand = new SkeletonPart("2th phalanx of the annular of the left hand", 0.033f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.033f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfAnnularOfLeftHand.addChildPart(phalanx2thOfAnnularOfLeftHand); skeletonParts.put(phalanx2thOfAnnularOfLeftHand.getId(), phalanx2thOfAnnularOfLeftHand);
			
			SkeletonPart phalanx3thOfAnnularOfLeftHand = new SkeletonPart("3th phalanx of the annular of the left hand", 0.022f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.022f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfAnnularOfLeftHand.addChildPart(phalanx3thOfAnnularOfLeftHand); skeletonParts.put(phalanx3thOfAnnularOfLeftHand.getId(), phalanx3thOfAnnularOfLeftHand);
			
			// ------------------------------------- Pinky of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfPinkyOfLeftHand = new SkeletonPart("1th phalanx of the pinky of the left hand", 0.035f).setPosition(new SkeletonPartPosition(1.1f, -0.055f, 0))
					.setBone(new Bone(0.035f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(rotateDimensionBuilder.build("Rotation:Left-Right", 45, -90, 0))
					.setRotZ(rotateDimensionBuilder.build("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			leftHand.addChildPart(phalanx1thOfPinkyOfLeftHand); skeletonParts.put(phalanx1thOfPinkyOfLeftHand.getId(), phalanx1thOfPinkyOfLeftHand);
			
			SkeletonPart phalanx2thOfPinkyOfLeftHand = new SkeletonPart("2th phalanx of the pinky of the left hand", 0.024f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.024f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfPinkyOfLeftHand.addChildPart(phalanx2thOfPinkyOfLeftHand); skeletonParts.put(phalanx2thOfPinkyOfLeftHand.getId(), phalanx2thOfPinkyOfLeftHand);
			
			SkeletonPart phalanx3thOfPinkyOfLeftHand = new SkeletonPart("3th phalanx of the pinky of the left hand", 0.021f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.021f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfPinkyOfLeftHand.addChildPart(phalanx3thOfPinkyOfLeftHand); skeletonParts.put(phalanx3thOfPinkyOfLeftHand.getId(), phalanx3thOfPinkyOfLeftHand);
				
		// ------------------------------------- Right leg ---------------------------------------

		SkeletonPart rightHip = new SkeletonPart("Right hip", 0.47f).setPosition(new SkeletonPartPosition(-0.5f, -0.05f, 0).setRotZ(-90))
				.setBone(new Bone(0.47f, 0.04f, 0).setLeftCapsuleRadius(0.05f).setRightCapsuleRadius(0.05f))
				.setRotY(rotateDimensionBuilder.build("Rotation:Back-Forward", 90, -180, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", 90, -180, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclowise-clockwise", -90, 90, 0))
				.setColor(0.2f, 0.9f, 0.2f);
		pelvis.addChildPart(rightHip); skeletonParts.put(rightHip.getId(), rightHip);
		
		SkeletonPart rightShin = new SkeletonPart("Right shin", 0.44f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.44f, 0.035f, 0).setLeftCapsuleRadius(0.045f).setRightCapsuleRadius(0.045f))
				.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, 170, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclowise-clockwise", -45, 45, 0))
				.setColor(0.2f, 0.8f, 0.2f);
		rightHip.addChildPart(rightShin); skeletonParts.put(rightShin.getId(), rightShin);
		
		SkeletonPart rightTarsus = new SkeletonPart("Right tarsus", 0.09f).setPosition(new SkeletonPartPosition(1f, 0, 0).setRotY(-90))
				.setBone(new Bone(0.9f, 0.04f, 0).setR1(0.05f).setR3(0.03f))
				.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 60, -45, 0))
				.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 45, -45, 0))
				.setColor(0.2f, 0.7f, 0.2f);
		rightShin.addChildPart(rightTarsus); skeletonParts.put(rightTarsus.getId(), rightTarsus);
		
		SkeletonPart rightMetatarsus = new SkeletonPart("Right metatarsus", 0.09f).setPosition(new SkeletonPartPosition(1f, 0, 0))
				.setBone(new Bone(0.09f, 0.04f, 0).setR1(0.03f).setR3(0.03f).setR4(0.07f))
				.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, 30, 10))
				.setColor(0.2f, 0.6f, 0.2f);
		rightTarsus.addChildPart(rightMetatarsus); skeletonParts.put(rightMetatarsus.getId(), rightMetatarsus);
		
		
			// ------------------------------------- Thumb of the right foot ---------------------------------------
		
			SkeletonPart phalanx1thOfThumbOfRightFoot = new SkeletonPart("1th phalanx of the thumb of the right foot", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0.05f, 0))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.015f).setRightCapsuleRadius(0.015f))
					.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 60, -90, -10))
					.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			rightMetatarsus.addChildPart(phalanx1thOfThumbOfRightFoot); skeletonParts.put(phalanx1thOfThumbOfRightFoot.getId(), phalanx1thOfThumbOfRightFoot);
			
			SkeletonPart phalanx2thOfThumbOfRightFoot = new SkeletonPart("2th phalanx of the thumb of the right foot", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.015f).setRightCapsuleRadius(0.015f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 60, -60, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfThumbOfRightFoot.addChildPart(phalanx2thOfThumbOfRightFoot); skeletonParts.put(phalanx2thOfThumbOfRightFoot.getId(), phalanx2thOfThumbOfRightFoot);
	

			// ------------------------------------- Forefinger of the right foot ---------------------------------------
			
			SkeletonPart phalanx1thOfForefingerOfRightFoot = new SkeletonPart("1th phalanx of the forefinger of the right foot", 0.03f).setPosition(new SkeletonPartPosition(1.1f, 0.025f, 0))
					.setBone(new Bone(0.03f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 60, -90, -10))
					.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			rightMetatarsus.addChildPart(phalanx1thOfForefingerOfRightFoot); skeletonParts.put(phalanx1thOfForefingerOfRightFoot.getId(), phalanx1thOfForefingerOfRightFoot);
			
			SkeletonPart phalanx2thOfForefingerOfRightFoot = new SkeletonPart("2th phalanx of the forefinger of the right foot", 0.025f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.025f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfForefingerOfRightFoot.addChildPart(phalanx2thOfForefingerOfRightFoot); skeletonParts.put(phalanx2thOfForefingerOfRightFoot.getId(), phalanx2thOfForefingerOfRightFoot);
			
			SkeletonPart phalanx3thOfForefingerOfRightFoot = new SkeletonPart("3th phalanx of the forefinger of the right foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.018f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", -90, 90, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfForefingerOfRightFoot.addChildPart(phalanx3thOfForefingerOfRightFoot); skeletonParts.put(phalanx3thOfForefingerOfRightFoot.getId(), phalanx3thOfForefingerOfRightFoot);
			
			// ------------------------------------- Middle finger of the right foot ---------------------------------------
			
			SkeletonPart phalanx1thOfMiddleFingerOfRightFoot = new SkeletonPart("1th phalanx of the middle finger of the right foot", 0.025f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.025f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 60, -90, -10))
					.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			rightMetatarsus.addChildPart(phalanx1thOfMiddleFingerOfRightFoot); skeletonParts.put(phalanx1thOfMiddleFingerOfRightFoot.getId(), phalanx1thOfMiddleFingerOfRightFoot);
			
			SkeletonPart phalanx2thOfMiddleFingerOfRightFoot = new SkeletonPart("2th phalanx of the middle finger of the right foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfMiddleFingerOfRightFoot.addChildPart(phalanx2thOfMiddleFingerOfRightFoot); skeletonParts.put(phalanx2thOfMiddleFingerOfRightFoot.getId(), phalanx2thOfMiddleFingerOfRightFoot);
			
			SkeletonPart phalanx3thOfMiddleFingerOfRightFoot = new SkeletonPart("3th phalanx of the middle finger of the right foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.018f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", -90, 90, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfMiddleFingerOfRightFoot.addChildPart(phalanx3thOfMiddleFingerOfRightFoot); skeletonParts.put(phalanx3thOfMiddleFingerOfRightFoot.getId(), phalanx3thOfMiddleFingerOfRightFoot);


			// ------------------------------------- Annular of the right foot ---------------------------------------
			
			SkeletonPart phalanx1thOfAnnularOfRightFoot = new SkeletonPart("1th phalanx of the annular of the right foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, -0.025f, 0))
					.setBone(new Bone(0.018f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 60, -90, -10))
					.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			rightMetatarsus.addChildPart(phalanx1thOfAnnularOfRightFoot); skeletonParts.put(phalanx1thOfAnnularOfRightFoot.getId(), phalanx1thOfAnnularOfRightFoot);
			
			SkeletonPart phalanx2thOfAnnularOfRightFoot = new SkeletonPart("2th phalanx of the annular of the right foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfAnnularOfRightFoot.addChildPart(phalanx2thOfAnnularOfRightFoot); skeletonParts.put(phalanx2thOfAnnularOfRightFoot.getId(), phalanx2thOfAnnularOfRightFoot);
			
			SkeletonPart phalanx3thOfAnnularOfRightFoot = new SkeletonPart("3th phalanx of the annular of the right foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", -90, 60, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfAnnularOfRightFoot.addChildPart(phalanx3thOfAnnularOfRightFoot); skeletonParts.put(phalanx3thOfAnnularOfRightFoot.getId(), phalanx3thOfAnnularOfRightFoot);
			
			// ------------------------------------- Pinky of the right foot ---------------------------------------
			
			SkeletonPart phalanx1thOfPinkyOfRightFoot = new SkeletonPart("1th phalanx of the pinky of the right foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, -0.05f, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 45, -90, -10))
					.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			rightMetatarsus.addChildPart(phalanx1thOfPinkyOfRightFoot); skeletonParts.put(phalanx1thOfPinkyOfRightFoot.getId(), phalanx1thOfPinkyOfRightFoot);
			
			SkeletonPart phalanx2thOfPinkyOfRightFoot = new SkeletonPart("2th phalanx of the pinky of the right foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfPinkyOfRightFoot.addChildPart(phalanx2thOfPinkyOfRightFoot); skeletonParts.put(phalanx2thOfPinkyOfRightFoot.getId(), phalanx2thOfPinkyOfRightFoot);
			
			SkeletonPart phalanx3thOfPinkyOfRightFoot = new SkeletonPart("3th phalanx of the pinky of the right foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", -30, 45, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfPinkyOfRightFoot.addChildPart(phalanx3thOfPinkyOfRightFoot); skeletonParts.put(phalanx3thOfPinkyOfRightFoot, phalanx3thOfPinkyOfRightFoot);
			
		// ------------------------------------- Left leg ---------------------------------------

		SkeletonPart leftHip = new SkeletonPart("Left hip", 0.47f).setPosition(new SkeletonPartPosition(0.5f, -0.05f, 0).setRotZ(-90))
				.setBone(new Bone(0.47f, 0.04f, 0).setLeftCapsuleRadius(0.05f).setRightCapsuleRadius(0.05f))
				.setRotY(rotateDimensionBuilder.build("Rotation:Back-Forward", 90, -180, 0))
				.setRotZ(rotateDimensionBuilder.build("Slope:Left-Right", 180, -90, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclowise-clockwise", -90, 90, 0))
				.setColor(0.2f, 0.9f, 0.2f);
		pelvis.addChildPart(leftHip); skeletonParts.put(leftHip.getId(), leftHip);
		
		SkeletonPart leftShin = new SkeletonPart("Left shin", 0.44f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.44f, 0.035f, 0).setLeftCapsuleRadius(0.045f).setRightCapsuleRadius(0.045f))
				.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, 170, 0))
				.setRotX(rotateDimensionBuilder.build("Rotation:Counterclowise-clockwise", -45, 45, 0))
				.setColor(0.2f, 0.8f, 0.2f);
		leftHip.addChildPart(leftShin); skeletonParts.put(leftShin.getId(), leftShin);
		
		SkeletonPart leftTarsus = new SkeletonPart("Left tarsus", 0.09f).setPosition(new SkeletonPartPosition(1f, 0, 0).setRotY(-90))
				.setBone(new Bone(0.9f, 0.04f, 0).setR1(0.05f).setR3(0.03f))
				.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 60, -45, 0))
				.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 45, -45, 0))
				.setColor(0.2f, 0.7f, 0.2f);
		leftShin.addChildPart(leftTarsus); skeletonParts.put(leftTarsus.getId(), leftTarsus);
		
		SkeletonPart leftMetatarsus = new SkeletonPart("Left metatarsus", 0.09f).setPosition(new SkeletonPartPosition(1f, 0, 0))
				.setBone(new Bone(0.09f, 0.04f, 0).setR1(0.03f).setR3(0.02f).setR4(0.07f))
				.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 0, 30, 10))
				.setColor(0.2f, 0.6f, 0.2f);
		leftTarsus.addChildPart(leftMetatarsus); skeletonParts.put(leftMetatarsus.getId(), leftMetatarsus);
		
		
			// ------------------------------------- Thumb of the left foot ---------------------------------------
		
			SkeletonPart phalanx1thOfThumbOfLeftFoot = new SkeletonPart("1th phalanx of the thumb of the left foot", 0.035f).setPosition(new SkeletonPartPosition(1.1f, -0.05f, 0))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.015f).setRightCapsuleRadius(0.015f))
					.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 60, -90, -10))
					.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			leftMetatarsus.addChildPart(phalanx1thOfThumbOfLeftFoot); skeletonParts.put(phalanx1thOfThumbOfLeftFoot.getId(), phalanx1thOfThumbOfLeftFoot);
			
			SkeletonPart phalanx2thOfThumbOfLeftFoot = new SkeletonPart("2th phalanx of the thumb of the left foot", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.015f).setRightCapsuleRadius(0.015f))
					.setRotY(rotateDimensionBuilder.build("Bending:Bend-Unbend", 60, -60, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfThumbOfLeftFoot.addChildPart(phalanx2thOfThumbOfLeftFoot); skeletonParts.put(phalanx2thOfThumbOfLeftFoot.getId(), phalanx2thOfThumbOfLeftFoot);
	

			// ------------------------------------- Forefinger of the left foot ---------------------------------------
			
			SkeletonPart phalanx1thOfForefingerOfLeftFoot = new SkeletonPart("1th phalanx of the forefinger of the left foot", 0.03f).setPosition(new SkeletonPartPosition(1.1f, -0.025f, 0))
					.setBone(new Bone(0.03f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 60, -90, -10))
					.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			leftMetatarsus.addChildPart(phalanx1thOfForefingerOfLeftFoot); skeletonParts.put(phalanx1thOfForefingerOfLeftFoot.getId(), phalanx1thOfForefingerOfLeftFoot);
			
			SkeletonPart phalanx2thOfForefingerOfLeftFoot = new SkeletonPart("2th phalanx of the forefinger of the left foot", 0.025f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.025f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfForefingerOfLeftFoot.addChildPart(phalanx2thOfForefingerOfLeftFoot); skeletonParts.put(phalanx2thOfForefingerOfLeftFoot.getId(), phalanx2thOfForefingerOfLeftFoot);
			
			SkeletonPart phalanx3thOfForefingerOfLeftFoot = new SkeletonPart("3th phalanx of the forefinger of the left foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.018f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", -90, 90, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfForefingerOfLeftFoot.addChildPart(phalanx3thOfForefingerOfLeftFoot); skeletonParts.put(phalanx3thOfForefingerOfLeftFoot.getId(), phalanx3thOfForefingerOfLeftFoot);
			
			// ------------------------------------- Middle finger of the left foot ---------------------------------------
			
			SkeletonPart phalanx1thOfMiddleFingerOfLeftFoot = new SkeletonPart("1th phalanx of the middle finger of the left foot", 0.025f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.025f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 60, -90, -10))
					.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			leftMetatarsus.addChildPart(phalanx1thOfMiddleFingerOfLeftFoot); skeletonParts.put(phalanx1thOfMiddleFingerOfLeftFoot.getId(), phalanx1thOfMiddleFingerOfLeftFoot);
			
			SkeletonPart phalanx2thOfMiddleFingerOfLeftFoot = new SkeletonPart("2th phalanx of the middle finger of the left foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfMiddleFingerOfLeftFoot.addChildPart(phalanx2thOfMiddleFingerOfLeftFoot); skeletonParts.put(phalanx2thOfMiddleFingerOfLeftFoot.getId(), phalanx2thOfMiddleFingerOfLeftFoot);
			
			SkeletonPart phalanx3thOfMiddleFingerOfLeftFoot = new SkeletonPart("3th phalanx of the middle finger of the left foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.018f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", -90, 90, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfMiddleFingerOfLeftFoot.addChildPart(phalanx3thOfMiddleFingerOfLeftFoot); skeletonParts.put(phalanx3thOfMiddleFingerOfLeftFoot.getId(), phalanx3thOfMiddleFingerOfLeftFoot);


			// ------------------------------------- Annular of the left foot ---------------------------------------
			
			SkeletonPart phalanx1thOfAnnularOfLeftFoot = new SkeletonPart("1th phalanx of the annular of the left foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, 0.025f, 0))
					.setBone(new Bone(0.018f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 60, -90, -10))
					.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			leftMetatarsus.addChildPart(phalanx1thOfAnnularOfLeftFoot); skeletonParts.put(phalanx1thOfAnnularOfLeftFoot.getId(), phalanx1thOfAnnularOfLeftFoot);
			
			SkeletonPart phalanx2thOfAnnularOfLeftFoot = new SkeletonPart("2th phalanx of the annular of the left foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfAnnularOfLeftFoot.addChildPart(phalanx2thOfAnnularOfLeftFoot); skeletonParts.put(phalanx2thOfAnnularOfLeftFoot.getId(), phalanx2thOfAnnularOfLeftFoot);
			
			SkeletonPart phalanx3thOfAnnularOfLeftFoot = new SkeletonPart("3th phalanx of the annular of the left foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", -90, 60, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfAnnularOfLeftFoot.addChildPart(phalanx3thOfAnnularOfLeftFoot); skeletonParts.put(phalanx3thOfAnnularOfLeftFoot.getId(), phalanx3thOfAnnularOfLeftFoot);
			
			// ------------------------------------- Pinky of the left foot ---------------------------------------
			
			SkeletonPart phalanx1thOfPinkyOfLeftFoot = new SkeletonPart("1th phalanx of the pinky of the left foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, 0.05f, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Slope:Down-Top", 45, -90, -10))
					.setRotZ(rotateDimensionBuilder.build("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			leftMetatarsus.addChildPart(phalanx1thOfPinkyOfLeftFoot); skeletonParts.put(phalanx1thOfPinkyOfLeftFoot.getId(), phalanx1thOfPinkyOfLeftFoot);
			
			SkeletonPart phalanx2thOfPinkyOfLeftFoot = new SkeletonPart("2th phalanx of the pinky of the left foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfPinkyOfLeftFoot.addChildPart(phalanx2thOfPinkyOfLeftFoot); skeletonParts.put(phalanx2thOfPinkyOfLeftFoot.getId(), phalanx2thOfPinkyOfLeftFoot);
			
			SkeletonPart phalanx3thOfPinkyOfLeftFoot = new SkeletonPart("3th phalanx of the pinky of the left foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(rotateDimensionBuilder.build("Bending:Unbend-Bend", -30, 45, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfPinkyOfLeftFoot.addChildPart(phalanx3thOfPinkyOfLeftFoot); skeletonParts.put(phalanx3thOfPinkyOfLeftFoot.getId(), phalanx3thOfPinkyOfLeftFoot);		    
	}
	
	public void draw(GL2 gl, boolean withPatrsColors) {
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glPushMatrix();
		gl.glLoadIdentity();
	    this.root.draw(gl, withPatrsColors);
	    gl.glPopMatrix();
	}
	
	public void draw(GL2 gl) {
	    this.root.draw(gl, false);
	}
	
	public SkeletonPart getActiveSkeletonPart(GL2 gl, int x, int y) {
		gl.glEnable(GL_BLEND);
		gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		gl.glDisable(GL_TEXTURE_2D);
		gl.glDisable(GL_FOG);
		gl.glDisable(GL_LIGHTING);
		draw(gl, true);
		IntBuffer viewPort = IntBuffer.allocate(4);
		gl.glGetIntegerv(GL_VIEWPORT, viewPort);
		ByteBuffer pixelColor = ByteBuffer.allocate(4);
		gl.glReadPixels(x, viewPort.get(3) - y, 1, 1, GL_RGBA, GL_BYTE, pixelColor);
		return root.getWithColor(new Color4b(pixelColor.array()));
	}
}
