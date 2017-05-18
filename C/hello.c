#include <board.h>
#include <fsl_debug_console.h>

int main (void) {
	
	hardware_init();
	while (1) {
		debug_printf("hello world");
	}
	return 0;
	
	
}