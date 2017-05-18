#include <fsl_device_registers.h>
#include <stdlib.h>
#include "utils.h"
#include <stdio.h>
#include <board.h>
#include <fsl_debug_console.h>


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
	
	hardware_init();
	
	PIN_Initialize();
	
	while (1) {
		if ((ADCRead_H() < 35000) & (ADCRead_V() > 60000)) {
			// NorthWest
			debug_printf("H");
		}
		else if ((ADCRead_H() < 35000) & (ADCRead_V() < 30000)) {
			// SouthWest
			debug_printf("F");
		} 
		else if (ADCRead_H() < 35000) {
			// West
			debug_printf("G");
		}
		else if ((ADCRead_H() > 65000) & (ADCRead_V() > 60000)) {
			// NorthEast
			debug_printf("B");
		}
		else if ((ADCRead_H() > 65000) & (ADCRead_V() < 30000)) {
			// SouthEast
			debug_printf("D");
		}
		else if (ADCRead_H() > 65000) {
			// East
			debug_printf("C");
		}
		else if (ADCRead_V() < 30000) {
			// South
			debug_printf("E");
		}
		else if (ADCRead_V() > 60000) {
			// North
			debug_printf("A");
		}
		else {
			// Center
			debug_printf("O");
		}
		delay();
		if (!GPIOC_PDIR) {
			//PTB->PCOR = 1 << 21;
			debug_printf("I");
			delay();
			
		}
	}
	
	return 0;
	
	
}

