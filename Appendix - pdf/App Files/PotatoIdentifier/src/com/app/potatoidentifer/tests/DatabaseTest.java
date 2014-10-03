package com.app.potatoidentifer.tests;

import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Assert;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.app.potatoidentifer.models.*;

public class DatabaseTest extends InstrumentationTestCase {

	FurtherInfoDataSource furtherInfo;
	GlossaryDataSource glossary;
	GlossaryCategoriesDataSource categories;
	DatabaseHelper dbhelper;
	protected void setUp() throws Exception {
		super.setUp();
		dbhelper = new DatabaseHelper(getInstrumentation().getContext());
		furtherInfo = new FurtherInfoDataSource(getInstrumentation().getContext());
		glossary = new GlossaryDataSource(getInstrumentation().getContext());
		categories = new GlossaryCategoriesDataSource(getInstrumentation().getContext());
		furtherInfo.open();
		glossary.open();
		categories.open();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetFutherInfo() throws Throwable
	{
		List<FurtherInfoBean> furtherinfobean = furtherInfo.getFurtherInfo("Potato Virus Y");
		for (int i = 0; i < furtherinfobean.size(); i++) 
		{
			Assert.assertEquals(furtherinfobean.get(i).getSymptom(), "Potato Virus Y");
			//TODO image test when available
			Assert.assertEquals(furtherinfobean.get(i).getBasicFacts(), "Occurs worldwide in potato, tobacco and vegetable crops (tomato, peppers, lettuce). Spread by many species of aphid and not effectively controlled by insecticide treatment. PVY causes yellowing and mosaic symptoms in leaves and in mixed infection with PVX symptoms can be severe yellow mosaic and leaf distortion." );
			Assert.assertEquals(furtherinfobean.get(i).getControl(), "If seed crop remove and destroy plant. If ware crop, mark the plants with a cane and harvest tubers for consumption. Tubers from infected plants should not be used for propagation.");
			Assert.assertEquals(furtherinfobean.get(i).getDiagnostics(), "RT-PCR test and ELISA and LFD ‘in field’ diagnostics available from www.neogeneurope.com and www.pocketdiagnostic.com.");
		}
	}
	
	public void testGetGlossaryInfo() throws Throwable
	{
		List<GlossaryBean> glossarybean = glossary.getGlossaryInfo("Insects + Pests");
		//TODO image test when avaliable
		Assert.assertEquals(glossarybean.get(0).getSymptom(), "Aphids");
		Assert.assertEquals(glossarybean.get(1).getSymptom(), "Tuber Moth");
		
	}
	
	public void testGetGlossaryCategories() throws Throwable
	{
		List<GlossaryCategoriesBean> glossarybean = categories.getGlossaryCategoryInfo();
		//TODO image test when avaliable
		Assert.assertEquals(glossarybean.get(0).getTitle(), "Insects + Pests");
		Assert.assertEquals(glossarybean.get(1).getTitle(), "Leaf");
		Assert.assertEquals(glossarybean.get(2).getTitle(), "Tubers");
		
	}

}
