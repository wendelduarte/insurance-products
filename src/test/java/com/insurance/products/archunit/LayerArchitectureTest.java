package com.insurance.products.archunit;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.insurance.products")
public class LayerArchitectureTest {

	private static final String CORE_LAYER_PACKAGES = "com.insurance.products.core..";
	private static final String DATAPROVIDER_LAYER_PACKAGES = "com.insurance.products.dataprovider..";
	private static final String ENTRYPOINT_LAYER_PACKAGES = "com.insurance.products.entrypoint..";
	
	@ArchTest
	static final ArchRule layerDependenciesAreRespected = layeredArchitecture()
			.layer(CORE_LAYER_PACKAGES).definedBy(CORE_LAYER_PACKAGES)
			.layer(DATAPROVIDER_LAYER_PACKAGES).definedBy(DATAPROVIDER_LAYER_PACKAGES)
			.layer(ENTRYPOINT_LAYER_PACKAGES).definedBy(ENTRYPOINT_LAYER_PACKAGES)
			
			.whereLayer(CORE_LAYER_PACKAGES).mayOnlyBeAccessedByLayers(DATAPROVIDER_LAYER_PACKAGES, ENTRYPOINT_LAYER_PACKAGES)
			.whereLayer(ENTRYPOINT_LAYER_PACKAGES).mayNotBeAccessedByAnyLayer()
			.whereLayer(DATAPROVIDER_LAYER_PACKAGES).mayNotBeAccessedByAnyLayer();
			
			
			
}
