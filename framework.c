#include <fsl_device_registers.h>
#include "utils.h"

/*
     Main program: entry point
*/



int main (void)
{
	PIN_Initialize();
	
	while (1) {
		if (!GPIOC_PDIR) {
			PTB->PCOR   = 1 << 22;
		} 
		else {
			PTB->PSOR   = 1 << 22;
		}
		if (!GPIOD_PDIR) {
			PTE->PCOR   = 1 << 26;
		}
		else {
			PTE->PSOR   = 1 << 26;
		}
	}
	
	
}

