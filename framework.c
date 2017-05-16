#include <fsl_device_registers.h>
#include <stdlib.h>
#include "utils.h"

/*
     Main program: entry point
*/

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


int main (void)
{
	PIN_Initialize();
	
	while (1) {
		if (ADCRead_H() < 35000 || ADCRead_H() > 60000) {
			PTB->PCOR   = 1 << 22;
		} 
		else {
			PTB->PSOR   = 1 << 22;
		}
		if (ADCRead_V() < 35000 || ADCRead_V() > 60000) {
			PTE->PCOR   = 1 << 26;
		}
		else {
			PTE->PSOR   = 1 << 26;
		} 
	}
	
	
}

