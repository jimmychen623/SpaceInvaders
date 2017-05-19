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
			PTB->PSOR = 1 << 21;
			if(!GPIOC_PDIR) {
				debug_printf("T\r\n");
			}
			else {
				debug_printf("H\r\n");
			}
			//fflush(stdout);
			delay();
		}
		else if ((ADCRead_H() < 35000) & (ADCRead_V() < 30000)) {
			// SouthWest
			PTB->PSOR = 1 << 21;
			if(!GPIOC_PDIR) {
				debug_printf("R\r\n");
			}
			else {
				debug_printf("F\r\n");
			}
			delay();
		} 
		else if (ADCRead_H() < 35000) {
			// West
			PTB->PSOR = 1 << 21;
			if(!GPIOC_PDIR) {
				debug_printf("S\r\n");
			}
			else {
				debug_printf("G\r\n");
			}
			delay();
		}
		else if ((ADCRead_H() > 65000) & (ADCRead_V() > 60000)) {
			// NorthEast
			PTB->PSOR = 1 << 21;
			if(!GPIOC_PDIR) {
				debug_printf("M\r\n");
			}
			else {
				debug_printf("B\r\n");
			}
			delay();
		}
		else if ((ADCRead_H() > 65000) & (ADCRead_V() < 30000)) {
			// SouthEast
			PTB->PSOR = 1 << 21;
			if(!GPIOC_PDIR) {
				debug_printf("P\r\n");
			}
			else {
				debug_printf("D\r\n");
			}
			delay();
		}
		else if (ADCRead_H() > 65000) {
			// East
			PTB->PSOR = 1 << 21;
			if(!GPIOC_PDIR) {
				debug_printf("N\r\n");
			}
			else {
				debug_printf("C\r\n");
			}
			delay();
		}
		else if (ADCRead_V() < 30000) {
			// South
			PTB->PSOR = 1 << 21;
			if(!GPIOC_PDIR) {
				debug_printf("Q\r\n");
			}
			else {
				debug_printf("E\r\n");
			}
			delay();
		}
		else if (ADCRead_V() > 60000) {
			// North
			PTB->PSOR = 1 << 21;
			if(!GPIOC_PDIR) {
				debug_printf("L\r\n");
			}
			else {
				debug_printf("A\r\n");
			}
			delay();
		}
		else {
			// Center
			PTB->PCOR = 1 << 21;
			if(!GPIOC_PDIR) {
				debug_printf("U\r\n");
			}
			else {
				debug_printf("O\r\n");
			}
			delay();
		}
	
	}
	
	return 0;
	
	
}

