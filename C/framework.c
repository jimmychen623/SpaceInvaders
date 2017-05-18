#include <fsl_device_registers.h>
#include <stdlib.h>
#include "utils.h"
#include <stdio.h>

unsigned short ADCRead_H (void) {
	ADC0_SC1A = 12 & ADC_SC1_ADCH_MASK;
	while(ADC0_SC2 & ADC_SC2_ADACT_MASK);
	while(!(ADC0_SC1A & ADC_SC1_COCO_MASK));
	return ADC0_RA;
}

unsigned short ADCRead_V (void) {
	ADC1_SC1A = 14 & ADC_SC1_ADCH_MASK;
	while(ADC1_SC2 & ADC_SC2_ADACT_MASK);
	while(!(ADC1_SC1A & ADC_SC1_COCO_MASK));
	return ADC1_RA;
}


int main (void) {
	
	//PIN_Initialize();
	FILE *fuck = fopen("./file.txt", "a");
	
	char *hi = "h";
	int age;
	fprintf(fuck,"%s", hi);
	//fputs("fuck C", fuck);
	//scanf("%d", &age);
	printf("my age is %d", age);
	//fflush(stdout);
	//fclose(fuck);
	while (0) {
		if (ADCRead_H() < 35000) {
			PTB->PCOR   = 1 << 22;
			printf("<L>");
			//fprintf(fuck,"<L>");
			fflush(stdout);
		} 
		else if (ADCRead_H() > 65000) {
			PTB->PCOR = 1 << 22;
			printf("<R>");
			fflush(stdout);
		}
		else {
			PTB->PSOR   = 1 << 22;
			printf("<0>");
			fflush(stdout);
		}
		if (ADCRead_V() < 30000) {
			PTE->PCOR   = 1 << 26;
			printf("<D>");
			fflush(stdout);
		}
		else if (ADCRead_V() > 60000) {
			PTE->PCOR = 1 << 26;
			printf("<U>");
			fflush(stdout);
		}
		else {
			PTE->PSOR   = 1 << 26;
			printf("<0>");
			fflush(stdout);
		} 
		if (!GPIOC_PDIR) {
			PTB->PCOR = 1 << 21;
			printf("<B>");
			fflush(stdout);
		}
		else {
			PTB->PSOR = 1 << 21;
			printf("<0>");
			fflush(stdout);
		}
	}
	
	return 0;
	
	
}

