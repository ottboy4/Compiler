     // MOVI R0,0x12345678

    //  MOVI R0,0
    //  ORRI R0,R0,0x00000078
    //  ORRI R0,R0,0x00005600
    //  ORRI R0,R0,0x00340000
    //  ORRI R0,R0,0x12000000

MOVI R7,0x80000000

/*
MOVI R6,0x40000000 // 0x12345678
MOVI R7,0x40000000
ADD R6,R6,R7

// R6 -> 0x12345678



CMP R6,R7

broked: B NE broked

// turn light on
MOVI R0,0x20000000
MOVI R1,0x00200000
ORR R0,R0,R1
MOVI R1,0x00040000
STRI R1,R0,4
MOVI R1,0x00010000
STRI R1,R0,40

stopped: B stopped
*/