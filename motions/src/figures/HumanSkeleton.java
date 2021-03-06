package figures;

import gui.dimensions.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.media.opengl.GL2;

import javax.vecmath.Color4b;



import static javax.media.opengl.GL.GL_TEXTURE_2D;
import static javax.media.opengl.GL2.*;
import static javax.media.opengl.GL2ES1.GL_FOG;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;

public class HumanSkeleton {
	private SkeletonPart root;
	private ArrayList<SkeletonPart> skeletonParts;
	
	public HumanSkeleton() {
		this.skeletonParts = new ArrayList<SkeletonPart>();
		
		//TODO: skeleton parts will load from database automatically
		SkeletonPart pelvis = new SkeletonPart("Pelvis", 0.3f)
				.setBone(new Bone(0.3f, 0.05f, -0.15f).setLeftCapsuleRadius(0.05f).setRightCapsuleRadius(0.05f))
				.setRotY(new RotateDimension("Rotation:Counterclockwise-Clockwise", 180, -180, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -180, 180, 0))
				.setRotX(new RotateDimension("Slope:Back-Forward", -180, 180, 0))
				.setColor(0.9f, 0.8f, 0.8f);
		skeletonParts.add(pelvis); this.root = pelvis; skeletonParts.add(pelvis);
		
		// ---------------------------------------- Spine ------------------------------------------

		SkeletonPart vertebra1th = new SkeletonPart("1th vertebra", 0.05f)
				.setPosition(new SkeletonPartPosition(0, 0.05f, 0).setRotZ(90))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		pelvis.addChildPart(vertebra1th); skeletonParts.add(vertebra1th);
		
		SkeletonPart vertebra2th = new SkeletonPart("2th vertebra", 0.05f)
				.setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.7f);
		vertebra1th.addChildPart(vertebra2th); skeletonParts.add(vertebra2th);
		
		SkeletonPart vertebra3th = new SkeletonPart("3th vertebra", 0.05f)
				.setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra2th.addChildPart(vertebra3th); skeletonParts.add(vertebra3th);
		
		SkeletonPart vertebra4th = new SkeletonPart("4th vertebra", 0.05f)
				.setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.7f);
		vertebra3th.addChildPart(vertebra4th); skeletonParts.add(vertebra4th);
		
		SkeletonPart vertebra5th = new SkeletonPart("5th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra4th.addChildPart(vertebra5th); skeletonParts.add(vertebra5th);
		
		SkeletonPart vertebra6th = new SkeletonPart("6th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.7f);
		vertebra5th.addChildPart(vertebra6th); skeletonParts.add(vertebra6th);
		
		SkeletonPart vertebra7th = new SkeletonPart("7th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra6th.addChildPart(vertebra7th); skeletonParts.add(vertebra7th);
		
		SkeletonPart vertebra8th = new SkeletonPart("8th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.7f);
		vertebra7th.addChildPart(vertebra8th); skeletonParts.add(vertebra8th);
		
		SkeletonPart vertebra9th = new SkeletonPart("9th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra8th.addChildPart(vertebra9th); skeletonParts.add(vertebra9th);
		
		SkeletonPart vertebra10th = new SkeletonPart("10th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.7f);
		vertebra9th.addChildPart(vertebra10th); skeletonParts.add(vertebra10th);

		SkeletonPart vertebra11th = new SkeletonPart("11th vertebra", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.05f, 0.03f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra10th.addChildPart(vertebra11th); skeletonParts.add(vertebra11th);
		
		SkeletonPart vertebra12th = new SkeletonPart("12th vertebra", 0.04f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.04f, 0.028f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.8f);
		vertebra11th.addChildPart(vertebra12th); skeletonParts.add(vertebra12th);
		
		SkeletonPart vertebra13th = new SkeletonPart("13th vertebra", 0.04f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.04f, 0.028f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra12th.addChildPart(vertebra13th); skeletonParts.add(vertebra13th);
		
		SkeletonPart vertebra14th = new SkeletonPart("14th vertebra", 0.04f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.04f, 0.028f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.8f);
		vertebra13th.addChildPart(vertebra14th); skeletonParts.add(vertebra14th);
		
		SkeletonPart vertebra15th = new SkeletonPart("15th vertebra", 0.04f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.04f, 0.028f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.5f, 0.7f, 0.5f);
		vertebra14th.addChildPart(vertebra15th); skeletonParts.add(vertebra15th);
		
		SkeletonPart vertebra16th = new SkeletonPart("16th vertebra", 0.04f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.04f, 0.028f, 0))
				.setRotY(new RotateDimension("Slope:Back-Forward", 10, -10, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -10, 10, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -10, 10, 0))
				.setStretchX(new StretchDimension("Tension:Squeeze-Stretch", 0.9f, 1.1f, 1.0f))
				.setColor(0.7f, 0.9f, 0.8f);
		vertebra15th.addChildPart(vertebra16th); skeletonParts.add(vertebra16th);
		
		// ---------------------------------------- Head ------------------------------------------
		
		SkeletonPart head = new SkeletonPart("Head", 0.12f).setPosition(new SkeletonPartPosition(1.0f, 0, 0.03f))
				.setBone(new Bone(0.12f, 0.07f, 0).setRightCapsuleRadius(0.06f))
				.setRotY(new RotateDimension("Slope:Back-Forward", 30, -30, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", -30, 30, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -30, 30, 0))
				.setColor(0.7f, 0.9f, 1.0f);
		vertebra16th.addChildPart(head); skeletonParts.add(head);
		
		// ------------------------------------- Right hand ---------------------------------------
		
		SkeletonPart rightCollarbone = new SkeletonPart("Right collarbone", 0.22f).setPosition(new SkeletonPartPosition(1.0f, 0.05f, 0).setRotZ(90))
				.setBone(new Bone(0.22f, 0.03f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(new RotateDimension("Rotation:Back-Forward", 30, -60, 0))
				.setRotZ(new RotateDimension("Slope:Down-Top", 10, -45, 0))
				.setColor(0.6f, 0.6f, 1.0f);
		vertebra11th.addChildPart(rightCollarbone); skeletonParts.add(rightCollarbone);
		
		SkeletonPart rightShoulder = new SkeletonPart("Right shoulder", 0.22f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.22f, 0.03f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(new RotateDimension("Rotation:Left-Right", -150, 30, 0))
				.setRotZ(new RotateDimension("Slope:Down-Top", 90, -90, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -90, 90, 0))
				.setColor(0.8f, 0.8f, 0.2f);
				
		rightCollarbone.addChildPart(rightShoulder); skeletonParts.add(rightShoulder);
		
		SkeletonPart rightElbow = new SkeletonPart("Right elbow", 0.2f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.2f, 0.028f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -150, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -150, 60, 0))
				.setColor(0.7f, 0.7f, 0.3f);
		rightShoulder.addChildPart(rightElbow); skeletonParts.add(rightElbow);
		
		SkeletonPart rightHand = new SkeletonPart("Right hand", 0.1f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.1f, 0.03f, 0).setR4(0.06f))
				.setRotY(new RotateDimension("Rotation:Left-Right", -90, 60, 0))
				.setRotZ(new RotateDimension("Slope:Down-Top", 45, -45, 0))
				.setColor(0.6f, 0.6f, 0.1f);
		rightElbow.addChildPart(rightHand); skeletonParts.add(rightHand);
		
			// ------------------------------------- Thumb of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfThumbOfRightHand = new SkeletonPart("1th phalanx of the thumb of the right hand", 0.05f).setPosition(new SkeletonPartPosition(0.1f, -0.04f, 0).setRotZ(-45))
					.setBone(new Bone(0.05f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(new RotateDimension("Rotation:Left-Right", -45, 10, 0))
					.setRotZ(new RotateDimension("Slope:Down-Top", 45, -15, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			rightHand.addChildPart(phalanx1thOfThumbOfRightHand); skeletonParts.add(phalanx1thOfThumbOfRightHand);
			
			SkeletonPart phalanx2thOfThumbOfRightHand = new SkeletonPart("2th phalanx of the thumb of the right hand", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0, 0).setRotZ(45).setRotX(-45))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -45, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfThumbOfRightHand.addChildPart(phalanx2thOfThumbOfRightHand); skeletonParts.add(phalanx2thOfThumbOfRightHand);
			
			SkeletonPart phalanx3thOfThumbOfRightHand = new SkeletonPart("3th phalanx of the thumb of the right hand", 0.03f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.03f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfThumbOfRightHand.addChildPart(phalanx3thOfThumbOfRightHand); skeletonParts.add(phalanx3thOfThumbOfRightHand);
			
			// ------------------------------------- Forefinger of the right hand ---------------------------------------
		
			SkeletonPart phalanx1thOfForefingerOfRightHand = new SkeletonPart("1th phalanx of the forefinger of the right hand", 0.045f).setPosition(new SkeletonPartPosition(1.1f, -0.045f, 0))
					.setBone(new Bone(0.045f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Rotation:Left-Right", -90, 45, 0))
					.setRotZ(new RotateDimension("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			rightHand.addChildPart(phalanx1thOfForefingerOfRightHand); skeletonParts.add(phalanx1thOfForefingerOfRightHand);
			
			SkeletonPart phalanx2thOfForefingerOfRightHand = new SkeletonPart("2th phalanx of the forefinger of the right hand", 0.03f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.03f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfForefingerOfRightHand.addChildPart(phalanx2thOfForefingerOfRightHand); skeletonParts.add(phalanx2thOfForefingerOfRightHand);
			
			SkeletonPart phalanx3thOfForefingerOfRightHand = new SkeletonPart("3th phalanx of the forefinger of the right hand", 0.022f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.022f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfForefingerOfRightHand.addChildPart(phalanx3thOfForefingerOfRightHand); skeletonParts.add(phalanx3thOfForefingerOfRightHand);
			
			// ------------------------------------- Middle finger of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfMiddleFingerOfRightHand = new SkeletonPart("1th phalanx of the middle finger of the right hand", 0.05f).setPosition(new SkeletonPartPosition(1.1f, -0.01f, 0))
					.setBone(new Bone(0.05f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Rotation:Left-Right", -90, 45, 0))
					.setRotZ(new RotateDimension("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			rightHand.addChildPart(phalanx1thOfMiddleFingerOfRightHand); skeletonParts.add(phalanx1thOfMiddleFingerOfRightHand);
			
			SkeletonPart phalanx2thOfMiddleFingerOfRightHand = new SkeletonPart("2th phalanx of the middle finger of the right hand", 0.033f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.033f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfMiddleFingerOfRightHand.addChildPart(phalanx2thOfMiddleFingerOfRightHand); skeletonParts.add(phalanx2thOfMiddleFingerOfRightHand);
			
			SkeletonPart phalanx3thOfMiddleFingerOfRightHand = new SkeletonPart("3th phalanx of the middle finger of the right hand", 0.023f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.023f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfMiddleFingerOfRightHand.addChildPart(phalanx3thOfMiddleFingerOfRightHand); skeletonParts.add(phalanx3thOfMiddleFingerOfRightHand);
			
			// ------------------------------------- Annular of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfAnnularOfRightHand = new SkeletonPart("1th phalanx of the annular of the right hand", 0.044f).setPosition(new SkeletonPartPosition(1.1f, 0.025f, 0))
					.setBone(new Bone(0.044f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Rotation:Left-Right", -90, 45, 0))
					.setRotZ(new RotateDimension("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			rightHand.addChildPart(phalanx1thOfAnnularOfRightHand); skeletonParts.add(phalanx1thOfAnnularOfRightHand);
			
			SkeletonPart phalanx2thOfAnnularOfRightHand = new SkeletonPart("2th phalanx of the annular of the right hand", 0.033f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.033f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfAnnularOfRightHand.addChildPart(phalanx2thOfAnnularOfRightHand); skeletonParts.add(phalanx2thOfAnnularOfRightHand);
			
			SkeletonPart phalanx3thOfAnnularOfRightHand = new SkeletonPart("3th phalanx of the annular of the right hand", 0.022f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.022f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfAnnularOfRightHand.addChildPart(phalanx3thOfAnnularOfRightHand); skeletonParts.add(phalanx3thOfAnnularOfRightHand);
			
			// ------------------------------------- Pinky of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfPinkyOfRightHand = new SkeletonPart("1th phalanx of the pinky of the right hand", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0.055f, 0))
					.setBone(new Bone(0.035f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(new RotateDimension("Rotation:Left-Right", -90, 45, 0))
					.setRotZ(new RotateDimension("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			rightHand.addChildPart(phalanx1thOfPinkyOfRightHand); skeletonParts.add(phalanx1thOfPinkyOfRightHand);
			
			SkeletonPart phalanx2thOfPinkyOfRightHand = new SkeletonPart("2th phalanx of the pinky of the right hand", 0.024f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.024f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfPinkyOfRightHand.addChildPart(phalanx2thOfPinkyOfRightHand); skeletonParts.add(phalanx2thOfPinkyOfRightHand);
			
			SkeletonPart phalanx3thOfPinkyOfRightHand = new SkeletonPart("3th phalanx of the pinky of the right hand", 0.021f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.021f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfPinkyOfRightHand.addChildPart(phalanx3thOfPinkyOfRightHand); skeletonParts.add(phalanx3thOfPinkyOfRightHand);
			

		// ------------------------------------- Left hand ---------------------------------------
		
		SkeletonPart leftCollarbone = new SkeletonPart("Left collarbone", 0.22f).setPosition(new SkeletonPartPosition(1.0f, -0.05f, 0).setRotZ(-90))
				.setBone(new Bone(0.22f, 0.03f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(new RotateDimension("Rotation:Back-Forward", 30, -60, 0))
				.setRotZ(new RotateDimension("Slope:Down-Top", -10, 45, 0))
				.setColor(0.6f, 0.6f, 1.0f);
		vertebra11th.addChildPart(leftCollarbone); skeletonParts.add(leftCollarbone);
		
		SkeletonPart leftShoulder = new SkeletonPart("Left shoulder", 0.22f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.22f, 0.03f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(new RotateDimension("Rotation:Left-Right", 30, -150, 0))
				.setRotZ(new RotateDimension("Slope:Down-Top", -90, 90, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -90, 90, 0))
				.setColor(0.8f, 0.8f, 0.2f);
		leftCollarbone.addChildPart(leftShoulder); skeletonParts.add(leftShoulder);
		
		SkeletonPart leftElbow = new SkeletonPart("Left elbow", 0.2f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.2f, 0.028f, 0).setLeftCapsuleRadius(0.04f).setRightCapsuleRadius(0.04f))
				.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -150, 0))
				.setRotX(new RotateDimension("Rotation:Counterclockwise-Clockwise", -150, 60, 0))
				.setColor(0.7f, 0.7f, 0.3f);
		leftShoulder.addChildPart(leftElbow); skeletonParts.add(leftElbow);
		
		SkeletonPart leftHand = new SkeletonPart("Left hand", 0.1f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.1f, 0.03f, 0).setR4(0.06f))
				.setRotY(new RotateDimension("Rotation:Left-Right", 60, -90, 0))
				.setRotZ(new RotateDimension("Slope:Down-Top", -45, 45, 0))
				.setColor(0.6f, 0.6f, 0.1f);
		leftElbow.addChildPart(leftHand); skeletonParts.add(leftHand);
			
			// ------------------------------------- Thumb of the left hand ---------------------------------------
			
			SkeletonPart phalanx1thOfThumbOfLeftHand = new SkeletonPart("1th phalanx of the thumb of the left hand", 0.05f).setPosition(new SkeletonPartPosition(0.1f, 0.04f, 0).setRotZ(45))
					.setBone(new Bone(0.05f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(new RotateDimension("Rotation:Left-Right", 10, -45, 0))
					.setRotZ(new RotateDimension("Slope:Down-Top", -15, 45, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			leftHand.addChildPart(phalanx1thOfThumbOfLeftHand); skeletonParts.add(phalanx1thOfThumbOfLeftHand);
			
			SkeletonPart phalanx2thOfThumbOfLeftHand = new SkeletonPart("2th phalanx of the thumb of the left hand", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0, 0).setRotZ(-45).setRotX(45))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -45, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfThumbOfLeftHand.addChildPart(phalanx2thOfThumbOfLeftHand); skeletonParts.add(phalanx2thOfThumbOfLeftHand);
			
			SkeletonPart phalanx3thOfThumbOfLeftHand = new SkeletonPart("3th phalanx of the thumb of the left hand", 0.03f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.03f, 0.01f, 0).setLeftCapsuleRadius(0.02f).setRightCapsuleRadius(0.02f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfThumbOfLeftHand.addChildPart(phalanx3thOfThumbOfLeftHand); skeletonParts.add(phalanx3thOfThumbOfLeftHand);
			
			// ------------------------------------- Forefinger of the left hand ---------------------------------------
		
			SkeletonPart phalanx1thOfForefingerOfLeftHand = new SkeletonPart("1th phalanx of the forefinger of the left hand", 0.045f).setPosition(new SkeletonPartPosition(1.1f, 0.045f, 0))
					.setBone(new Bone(0.045f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Rotation:Left-Right", 45, -90, 0))
					.setRotZ(new RotateDimension("Slope:Down-Top", -30, 30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			leftHand.addChildPart(phalanx1thOfForefingerOfLeftHand); skeletonParts.add(phalanx1thOfForefingerOfLeftHand);
			
			SkeletonPart phalanx2thOfForefingerOfLeftHand = new SkeletonPart("2th phalanx of the forefinger of the left hand", 0.03f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.03f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfForefingerOfLeftHand.addChildPart(phalanx2thOfForefingerOfLeftHand); skeletonParts.add(phalanx2thOfForefingerOfLeftHand);
			
			SkeletonPart phalanx3thOfForefingerOfLeftHand = new SkeletonPart("3th phalanx of the forefinger of the left hand", 0.022f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.022f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfForefingerOfLeftHand.addChildPart(phalanx3thOfForefingerOfLeftHand); skeletonParts.add(phalanx3thOfForefingerOfLeftHand);
			
			// ------------------------------------- Middle finger of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfMiddleFingerOfLeftHand = new SkeletonPart("1th phalanx of the middle finger of the left hand", 0.05f).setPosition(new SkeletonPartPosition(1.1f, 0.01f, 0))
					.setBone(new Bone(0.05f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Rotation:Left-Right", 45, -90, 0))
					.setRotZ(new RotateDimension("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			leftHand.addChildPart(phalanx1thOfMiddleFingerOfLeftHand); skeletonParts.add(phalanx1thOfMiddleFingerOfLeftHand);
			
			SkeletonPart phalanx2thOfMiddleFingerOfLeftHand = new SkeletonPart("2th phalanx of the middle finger of the left hand", 0.033f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.033f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfMiddleFingerOfLeftHand.addChildPart(phalanx2thOfMiddleFingerOfLeftHand); skeletonParts.add(phalanx2thOfMiddleFingerOfLeftHand);
			
			SkeletonPart phalanx3thOfMiddleFingerOfLeftHand = new SkeletonPart("3th phalanx of the middle finger of the left hand", 0.023f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.023f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfMiddleFingerOfLeftHand.addChildPart(phalanx3thOfMiddleFingerOfLeftHand); skeletonParts.add(phalanx3thOfMiddleFingerOfLeftHand);
			
			// ------------------------------------- Annular of the left hand ---------------------------------------
			
			SkeletonPart phalanx1thOfAnnularOfLeftHand = new SkeletonPart("1th phalanx of the annular of the left hand", 0.044f).setPosition(new SkeletonPartPosition(1.1f, -0.025f, 0))
					.setBone(new Bone(0.044f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Rotation:Left-Right", 45, -90, 0))
					.setRotZ(new RotateDimension("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			leftHand.addChildPart(phalanx1thOfAnnularOfLeftHand); skeletonParts.add(phalanx1thOfAnnularOfLeftHand);
			
			SkeletonPart phalanx2thOfAnnularOfLeftHand = new SkeletonPart("2th phalanx of the annular of the left hand", 0.033f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.033f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfAnnularOfLeftHand.addChildPart(phalanx2thOfAnnularOfLeftHand); skeletonParts.add(phalanx2thOfAnnularOfLeftHand);
			
			SkeletonPart phalanx3thOfAnnularOfLeftHand = new SkeletonPart("3th phalanx of the annular of the left hand", 0.022f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.022f, 0.01f, 0).setLeftCapsuleRadius(0.017f).setRightCapsuleRadius(0.017f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfAnnularOfLeftHand.addChildPart(phalanx3thOfAnnularOfLeftHand); skeletonParts.add(phalanx3thOfAnnularOfLeftHand);
			
			// ------------------------------------- Pinky of the right hand ---------------------------------------
			
			SkeletonPart phalanx1thOfPinkyOfLeftHand = new SkeletonPart("1th phalanx of the pinky of the left hand", 0.035f).setPosition(new SkeletonPartPosition(1.1f, -0.055f, 0))
					.setBone(new Bone(0.035f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(new RotateDimension("Rotation:Left-Right", 45, -90, 0))
					.setRotZ(new RotateDimension("Slope:Down-Top", 30, -30, 0))
					.setColor(0.5f, 0.5f, 0.1f);
			leftHand.addChildPart(phalanx1thOfPinkyOfLeftHand); skeletonParts.add(phalanx1thOfPinkyOfLeftHand);
			
			SkeletonPart phalanx2thOfPinkyOfLeftHand = new SkeletonPart("2th phalanx of the pinky of the left hand", 0.024f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.024f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -120, 0))
					.setColor(0.4f, 0.4f, 0.1f);
			phalanx1thOfPinkyOfLeftHand.addChildPart(phalanx2thOfPinkyOfLeftHand); skeletonParts.add(phalanx2thOfPinkyOfLeftHand);
			
			SkeletonPart phalanx3thOfPinkyOfLeftHand = new SkeletonPart("3th phalanx of the pinky of the left hand", 0.021f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.021f, 0.007f, 0).setLeftCapsuleRadius(0.014f).setRightCapsuleRadius(0.014f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, -90, 0))
					.setColor(0.3f, 0.3f, 0.1f);
			phalanx2thOfPinkyOfLeftHand.addChildPart(phalanx3thOfPinkyOfLeftHand); skeletonParts.add(phalanx3thOfPinkyOfLeftHand);
				
		// ------------------------------------- Right leg ---------------------------------------

		SkeletonPart rightHip = new SkeletonPart("Right hip", 0.47f).setPosition(new SkeletonPartPosition(-0.5f, -0.05f, 0).setRotZ(-90))
				.setBone(new Bone(0.47f, 0.04f, 0).setLeftCapsuleRadius(0.05f).setRightCapsuleRadius(0.05f))
				.setRotY(new RotateDimension("Rotation:Back-Forward", 90, -180, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", 90, -180, 0))
				.setRotX(new RotateDimension("Rotation:Counterclowise-clockwise", -90, 90, 0))
				.setColor(0.2f, 0.9f, 0.2f);
		pelvis.addChildPart(rightHip); skeletonParts.add(rightHip);
		
		SkeletonPart rightShin = new SkeletonPart("Right shin", 0.44f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.44f, 0.035f, 0).setLeftCapsuleRadius(0.045f).setRightCapsuleRadius(0.045f))
				.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, 170, 0))
				.setRotX(new RotateDimension("Rotation:Counterclowise-clockwise", -45, 45, 0))
				.setColor(0.2f, 0.8f, 0.2f);
		rightHip.addChildPart(rightShin); skeletonParts.add(rightShin);
		
		SkeletonPart rightTarsus = new SkeletonPart("Right tarsus", 0.09f).setPosition(new SkeletonPartPosition(1f, 0, 0).setRotY(-90))
				.setBone(new Bone(0.9f, 0.04f, 0).setR1(0.05f).setR3(0.03f))
				.setRotY(new RotateDimension("Slope:Down-Top", 60, -45, 0))
				.setRotZ(new RotateDimension("Rotation:Left-Right", 45, -45, 0))
				.setColor(0.2f, 0.7f, 0.2f);
		rightShin.addChildPart(rightTarsus); skeletonParts.add(rightTarsus);
		
		SkeletonPart rightMetatarsus = new SkeletonPart("Right metatarsus", 0.09f).setPosition(new SkeletonPartPosition(1f, 0, 0))
				.setBone(new Bone(0.09f, 0.04f, 0).setR1(0.03f).setR3(0.03f).setR4(0.07f))
				.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, 30, 10))
				.setColor(0.2f, 0.6f, 0.2f);
		rightTarsus.addChildPart(rightMetatarsus); skeletonParts.add(rightMetatarsus);
		
		
			// ------------------------------------- Thumb of the right foot ---------------------------------------
		
			SkeletonPart phalanx1thOfThumbOfRightFoot = new SkeletonPart("1th phalanx of the thumb of the right foot", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0.05f, 0))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.015f).setRightCapsuleRadius(0.015f))
					.setRotY(new RotateDimension("Slope:Down-Top", 60, -90, -10))
					.setRotZ(new RotateDimension("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			rightMetatarsus.addChildPart(phalanx1thOfThumbOfRightFoot); skeletonParts.add(phalanx1thOfThumbOfRightFoot);
			
			SkeletonPart phalanx2thOfThumbOfRightFoot = new SkeletonPart("2th phalanx of the thumb of the right foot", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.015f).setRightCapsuleRadius(0.015f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 60, -60, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfThumbOfRightFoot.addChildPart(phalanx2thOfThumbOfRightFoot); skeletonParts.add(phalanx2thOfThumbOfRightFoot);
	

			// ------------------------------------- Forefinger of the right foot ---------------------------------------
			
			SkeletonPart phalanx1thOfForefingerOfRightFoot = new SkeletonPart("1th phalanx of the forefinger of the right foot", 0.03f).setPosition(new SkeletonPartPosition(1.1f, 0.025f, 0))
					.setBone(new Bone(0.03f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Slope:Down-Top", 60, -90, -10))
					.setRotZ(new RotateDimension("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			rightMetatarsus.addChildPart(phalanx1thOfForefingerOfRightFoot); skeletonParts.add(phalanx1thOfForefingerOfRightFoot);
			
			SkeletonPart phalanx2thOfForefingerOfRightFoot = new SkeletonPart("2th phalanx of the forefinger of the right foot", 0.025f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.025f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfForefingerOfRightFoot.addChildPart(phalanx2thOfForefingerOfRightFoot); skeletonParts.add(phalanx2thOfForefingerOfRightFoot);
			
			SkeletonPart phalanx3thOfForefingerOfRightFoot = new SkeletonPart("3th phalanx of the forefinger of the right foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.018f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", -90, 90, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfForefingerOfRightFoot.addChildPart(phalanx3thOfForefingerOfRightFoot); skeletonParts.add(phalanx3thOfForefingerOfRightFoot);
			
			// ------------------------------------- Middle finger of the right foot ---------------------------------------
			
			SkeletonPart phalanx1thOfMiddleFingerOfRightFoot = new SkeletonPart("1th phalanx of the middle finger of the right foot", 0.025f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.025f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Slope:Down-Top", 60, -90, -10))
					.setRotZ(new RotateDimension("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			rightMetatarsus.addChildPart(phalanx1thOfMiddleFingerOfRightFoot); skeletonParts.add(phalanx1thOfMiddleFingerOfRightFoot);
			
			SkeletonPart phalanx2thOfMiddleFingerOfRightFoot = new SkeletonPart("2th phalanx of the middle finger of the right foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfMiddleFingerOfRightFoot.addChildPart(phalanx2thOfMiddleFingerOfRightFoot); skeletonParts.add(phalanx2thOfMiddleFingerOfRightFoot);
			
			SkeletonPart phalanx3thOfMiddleFingerOfRightFoot = new SkeletonPart("3th phalanx of the middle finger of the right foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.018f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", -90, 90, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfMiddleFingerOfRightFoot.addChildPart(phalanx3thOfMiddleFingerOfRightFoot); skeletonParts.add(phalanx3thOfMiddleFingerOfRightFoot);


			// ------------------------------------- Annular of the right foot ---------------------------------------
			
			SkeletonPart phalanx1thOfAnnularOfRightFoot = new SkeletonPart("1th phalanx of the annular of the right foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, -0.025f, 0))
					.setBone(new Bone(0.018f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Slope:Down-Top", 60, -90, -10))
					.setRotZ(new RotateDimension("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			rightMetatarsus.addChildPart(phalanx1thOfAnnularOfRightFoot); skeletonParts.add(phalanx1thOfAnnularOfRightFoot);
			
			SkeletonPart phalanx2thOfAnnularOfRightFoot = new SkeletonPart("2th phalanx of the annular of the right foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfAnnularOfRightFoot.addChildPart(phalanx2thOfAnnularOfRightFoot); skeletonParts.add(phalanx2thOfAnnularOfRightFoot);
			
			SkeletonPart phalanx3thOfAnnularOfRightFoot = new SkeletonPart("3th phalanx of the annular of the right foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", -90, 60, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfAnnularOfRightFoot.addChildPart(phalanx3thOfAnnularOfRightFoot); skeletonParts.add(phalanx3thOfAnnularOfRightFoot);
			
			// ------------------------------------- Pinky of the right foot ---------------------------------------
			
			SkeletonPart phalanx1thOfPinkyOfRightFoot = new SkeletonPart("1th phalanx of the pinky of the right foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, -0.05f, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Slope:Down-Top", 45, -90, -10))
					.setRotZ(new RotateDimension("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			rightMetatarsus.addChildPart(phalanx1thOfPinkyOfRightFoot); skeletonParts.add(phalanx1thOfPinkyOfRightFoot);
			
			SkeletonPart phalanx2thOfPinkyOfRightFoot = new SkeletonPart("2th phalanx of the pinky of the right foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfPinkyOfRightFoot.addChildPart(phalanx2thOfPinkyOfRightFoot); skeletonParts.add(phalanx2thOfPinkyOfRightFoot);
			
			SkeletonPart phalanx3thOfPinkyOfRightFoot = new SkeletonPart("3th phalanx of the pinky of the right foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", -30, 45, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfPinkyOfRightFoot.addChildPart(phalanx3thOfPinkyOfRightFoot); skeletonParts.add(phalanx3thOfPinkyOfRightFoot);
			
		// ------------------------------------- Left leg ---------------------------------------

		SkeletonPart leftHip = new SkeletonPart("Left hip", 0.47f).setPosition(new SkeletonPartPosition(0.5f, -0.05f, 0).setRotZ(-90))
				.setBone(new Bone(0.47f, 0.04f, 0).setLeftCapsuleRadius(0.05f).setRightCapsuleRadius(0.05f))
				.setRotY(new RotateDimension("Rotation:Back-Forward", 90, -180, 0))
				.setRotZ(new RotateDimension("Slope:Left-Right", 180, -90, 0))
				.setRotX(new RotateDimension("Rotation:Counterclowise-clockwise", -90, 90, 0))
				.setColor(0.2f, 0.9f, 0.2f);
		pelvis.addChildPart(leftHip); skeletonParts.add(leftHip);
		
		SkeletonPart leftShin = new SkeletonPart("Left shin", 0.44f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
				.setBone(new Bone(0.44f, 0.035f, 0).setLeftCapsuleRadius(0.045f).setRightCapsuleRadius(0.045f))
				.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, 170, 0))
				.setRotX(new RotateDimension("Rotation:Counterclowise-clockwise", -45, 45, 0))
				.setColor(0.2f, 0.8f, 0.2f);
		leftHip.addChildPart(leftShin); skeletonParts.add(leftShin);
		
		SkeletonPart leftTarsus = new SkeletonPart("Left tarsus", 0.09f).setPosition(new SkeletonPartPosition(1f, 0, 0).setRotY(-90))
				.setBone(new Bone(0.9f, 0.04f, 0).setR1(0.05f).setR3(0.03f))
				.setRotY(new RotateDimension("Slope:Down-Top", 60, -45, 0))
				.setRotZ(new RotateDimension("Rotation:Left-Right", 45, -45, 0))
				.setColor(0.2f, 0.7f, 0.2f);
		leftShin.addChildPart(leftTarsus); skeletonParts.add(leftTarsus);
		
		SkeletonPart leftMetatarsus = new SkeletonPart("Left metatarsus", 0.09f).setPosition(new SkeletonPartPosition(1f, 0, 0))
				.setBone(new Bone(0.09f, 0.04f, 0).setR1(0.03f).setR3(0.02f).setR4(0.07f))
				.setRotY(new RotateDimension("Bending:Bend-Unbend", 0, 30, 10))
				.setColor(0.2f, 0.6f, 0.2f);
		leftTarsus.addChildPart(leftMetatarsus); skeletonParts.add(leftMetatarsus);
		
		
			// ------------------------------------- Thumb of the left foot ---------------------------------------
		
			SkeletonPart phalanx1thOfThumbOfLeftFoot = new SkeletonPart("1th phalanx of the thumb of the left foot", 0.035f).setPosition(new SkeletonPartPosition(1.1f, -0.05f, 0))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.015f).setRightCapsuleRadius(0.015f))
					.setRotY(new RotateDimension("Slope:Down-Top", 60, -90, -10))
					.setRotZ(new RotateDimension("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			leftMetatarsus.addChildPart(phalanx1thOfThumbOfLeftFoot); skeletonParts.add(phalanx1thOfThumbOfLeftFoot);
			
			SkeletonPart phalanx2thOfThumbOfLeftFoot = new SkeletonPart("2th phalanx of the thumb of the left foot", 0.035f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.035f, 0.01f, 0).setLeftCapsuleRadius(0.015f).setRightCapsuleRadius(0.015f))
					.setRotY(new RotateDimension("Bending:Bend-Unbend", 60, -60, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfThumbOfLeftFoot.addChildPart(phalanx2thOfThumbOfLeftFoot); skeletonParts.add(phalanx2thOfThumbOfLeftFoot);
	

			// ------------------------------------- Forefinger of the left foot ---------------------------------------
			
			SkeletonPart phalanx1thOfForefingerOfLeftFoot = new SkeletonPart("1th phalanx of the forefinger of the left foot", 0.03f).setPosition(new SkeletonPartPosition(1.1f, -0.025f, 0))
					.setBone(new Bone(0.03f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Slope:Down-Top", 60, -90, -10))
					.setRotZ(new RotateDimension("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			leftMetatarsus.addChildPart(phalanx1thOfForefingerOfLeftFoot); skeletonParts.add(phalanx1thOfForefingerOfLeftFoot);
			
			SkeletonPart phalanx2thOfForefingerOfLeftFoot = new SkeletonPart("2th phalanx of the forefinger of the left foot", 0.025f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.025f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfForefingerOfLeftFoot.addChildPart(phalanx2thOfForefingerOfLeftFoot); skeletonParts.add(phalanx2thOfForefingerOfLeftFoot);
			
			SkeletonPart phalanx3thOfForefingerOfLeftFoot = new SkeletonPart("3th phalanx of the forefinger of the left foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.018f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", -90, 90, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfForefingerOfLeftFoot.addChildPart(phalanx3thOfForefingerOfLeftFoot); skeletonParts.add(phalanx3thOfForefingerOfLeftFoot);
			
			// ------------------------------------- Middle finger of the left foot ---------------------------------------
			
			SkeletonPart phalanx1thOfMiddleFingerOfLeftFoot = new SkeletonPart("1th phalanx of the middle finger of the left foot", 0.025f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.025f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Slope:Down-Top", 60, -90, -10))
					.setRotZ(new RotateDimension("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			leftMetatarsus.addChildPart(phalanx1thOfMiddleFingerOfLeftFoot); skeletonParts.add(phalanx1thOfMiddleFingerOfLeftFoot);
			
			SkeletonPart phalanx2thOfMiddleFingerOfLeftFoot = new SkeletonPart("2th phalanx of the middle finger of the left foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfMiddleFingerOfLeftFoot.addChildPart(phalanx2thOfMiddleFingerOfLeftFoot); skeletonParts.add(phalanx2thOfMiddleFingerOfLeftFoot);
			
			SkeletonPart phalanx3thOfMiddleFingerOfLeftFoot = new SkeletonPart("3th phalanx of the middle finger of the left foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.018f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", -90, 90, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfMiddleFingerOfLeftFoot.addChildPart(phalanx3thOfMiddleFingerOfLeftFoot); skeletonParts.add(phalanx3thOfMiddleFingerOfLeftFoot);


			// ------------------------------------- Annular of the left foot ---------------------------------------
			
			SkeletonPart phalanx1thOfAnnularOfLeftFoot = new SkeletonPart("1th phalanx of the annular of the left foot", 0.018f).setPosition(new SkeletonPartPosition(1.1f, 0.025f, 0))
					.setBone(new Bone(0.018f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Slope:Down-Top", 60, -90, -10))
					.setRotZ(new RotateDimension("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			leftMetatarsus.addChildPart(phalanx1thOfAnnularOfLeftFoot); skeletonParts.add(phalanx1thOfAnnularOfLeftFoot);
			
			SkeletonPart phalanx2thOfAnnularOfLeftFoot = new SkeletonPart("2th phalanx of the annular of the left foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfAnnularOfLeftFoot.addChildPart(phalanx2thOfAnnularOfLeftFoot); skeletonParts.add(phalanx2thOfAnnularOfLeftFoot);
			
			SkeletonPart phalanx3thOfAnnularOfLeftFoot = new SkeletonPart("3th phalanx of the annular of the left foot", 0.015f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.015f, 0.008f, 0).setLeftCapsuleRadius(0.013f).setRightCapsuleRadius(0.013f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", -90, 60, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfAnnularOfLeftFoot.addChildPart(phalanx3thOfAnnularOfLeftFoot); skeletonParts.add(phalanx3thOfAnnularOfLeftFoot);
			
			// ------------------------------------- Pinky of the left foot ---------------------------------------
			
			SkeletonPart phalanx1thOfPinkyOfLeftFoot = new SkeletonPart("1th phalanx of the pinky of the left foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, 0.05f, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Slope:Down-Top", 45, -90, -10))
					.setRotZ(new RotateDimension("Rotation:Left-Right", 15, -15, 0))
					.setColor(0.2f, 0.5f, 0.2f);
			leftMetatarsus.addChildPart(phalanx1thOfPinkyOfLeftFoot); skeletonParts.add(phalanx1thOfPinkyOfLeftFoot);
			
			SkeletonPart phalanx2thOfPinkyOfLeftFoot = new SkeletonPart("2th phalanx of the pinky of the left foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", 0, 90, 0))
					.setColor(0.2f, 0.4f, 0.2f);
			phalanx1thOfPinkyOfLeftFoot.addChildPart(phalanx2thOfPinkyOfLeftFoot); skeletonParts.add(phalanx2thOfPinkyOfLeftFoot);
			
			SkeletonPart phalanx3thOfPinkyOfLeftFoot = new SkeletonPart("3th phalanx of the pinky of the left foot", 0.01f).setPosition(new SkeletonPartPosition(1.1f, 0, 0))
					.setBone(new Bone(0.01f, 0.007f, 0).setLeftCapsuleRadius(0.012f).setRightCapsuleRadius(0.012f))
					.setRotY(new RotateDimension("Bending:Unbend-Bend", -30, 45, 0))
					.setColor(0.2f, 0.3f, 0.2f);
			phalanx2thOfPinkyOfLeftFoot.addChildPart(phalanx3thOfPinkyOfLeftFoot); skeletonParts.add(phalanx3thOfPinkyOfLeftFoot);		    
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
