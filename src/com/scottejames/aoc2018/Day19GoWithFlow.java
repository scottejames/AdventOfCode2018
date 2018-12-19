package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

/**
 #ip 0
 seti 5 0 1
 seti 6 0 2
 addi 0 1 0
 addr 1 2 3
 setr 1 0 0
 seti 8 0 4
 seti 9 0 5
 */

public class Day19GoWithFlow extends AocDay{

    int[] reg = new int[6];
    List<Operation> instr;
    int instructionPointer = 0;
    int boundRegister;

    @Override
    public void runDay() {
        parseInput();
        reg[0] = 1;
        //200_000         [0, 24998,    10551432, 1, 0, 5]
        //200_000_000     [6, 3897133,  10551432, 3, 0, 5]
        //400_000_000     [10, 7794268, 10551432, 5, 0, 5]
        //800_000_000     [24, 5037106, 10551432, 10, 0, 10]
        //1_000_000_000   [36, 8934241, 10551432, 12, 0, 10]



        for (long i = 0; i < 1_000_000L; i ++){
 //       while (instructionPointer < instr.size()) {
            boolean found = runProgram();
            if (found) break;
        }
        System.out.println("[" + reg[0] + ", "+ reg[1] + ", "+ reg[2]  +
                ", "+ reg[3] + ", "+ reg[4]  +", "+ reg[5] + "]");
        System.out.println("Part 1 " +  reg[0]);
        System.out.println(sumOfFactors(10551432));

    }
    // https://www.mathsisfun.com/numbers/factors-all-tool.html
    // 27024480
    int sumOfFactors(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            if (i % n == 0) {
                sum += i;
            }
        }
        return sum;
    }

    public boolean runProgram(){
        reg[boundRegister] = instructionPointer;

        Operation op = instr.get(instructionPointer);
        op.cmd.setRegisters(reg);
//        System.out.println("BEFORE ip=" + instructionPointer + "[" + reg[0] + ", "+ reg[1] + ", "+ reg[2]  +
//                ", "+ reg[3] + ", "+ reg[4]  +", "+ reg[5] + "]");
//        System.out.println(op.cmd + " " + op.a + ", " + op.b + ", " + op.c);

        int result = op.cmd.opcode.applyAsInt(op);
        reg[op.c] = result;

     //   if (instructionPointer == 32) return true;

        instructionPointer = reg[boundRegister];
        instructionPointer++;
//        System.out.println("AFTER ip=" + instructionPointer + "[" + reg[0] + ", "+ reg[1] + ", "+ reg[2]  +
//                ", "+ reg[3] + ", "+ reg[4]  +", "+ reg[5] + "]");
        return false;
    }
    public void parseInput(){
        instr = new ArrayList<>();
        List<String> items = this.getDataFromFile("2018/DayNineteen_data.txt");

        for (String item: items){

            String[] split = item.split(" ");
            if (split.length == 2){
                boundRegister = Integer.parseInt(split[1]);
            } else {
                Command cmd = Command.valueOf(split[0].toUpperCase());
                int a = Integer.parseInt(split[1]);
                int b = Integer.parseInt(split[2]);
                int c = Integer.parseInt(split[3]);
                Operation op = new Operation(cmd, a, b, c);
                instr.add(op);
            }
        }
    }
    class Operation{

        Command cmd;
        int a;
        int b;
        int c;

        public Operation(Command cmd, int a, int b, int c) {
            this.cmd = cmd;
            this.a = a;
            this.b = b;
            this.c = c;
        }


    }
    enum Command {
        GTRR((x, y) -> x > y ? 1 : 0, Command::rr),
        BORR((x, y) -> x | y, Command::rr),
        GTIR((x, y) -> x > y ? 1 : 0, Command::ir),
        EQRI((x, y) -> x == y ? 1 : 0, Command::ri),
        ADDR((x, y) -> x + y, Command::rr),
        SETI((x, y) -> x, Command::i),
        EQRR((x, y) -> x == y ? 1 : 0, Command::rr),
        GTRI((x, y) -> x > y ? 1 : 0, Command::ri),
        BANR((x, y) -> x & y, Command::rr),
        ADDI((x, y) -> x + y, Command::ri),
        SETR((x, y) -> x, Command::r),
        MULR((x, y) -> x * y, Command::rr),
        BORI((x, y) -> x | y, Command::ri),
        MULI((x, y) -> x * y, Command::ri),
        EQIR((x, y) -> x == y ? 1 : 0, Command::ir),
        BANI((x, y) -> x & y, Command::ri);

        private static int[] registers;

        public static int r(Operation op) {
            return op.cmd.function.applyAsInt(registers[op.a], 0);
        }

        public static int i(Operation op) {
            return op.cmd.function.applyAsInt(op.a,0);
        }


        public static int ri(Operation op) {
            return op.cmd.function.applyAsInt(registers[op.a], op.b);
        }

        public static int rr(Operation op) {
            return op.cmd.function.applyAsInt(registers[op.a], registers[op.b]);
        }

        public static int ir(Operation op) {
            return op.cmd.function.applyAsInt(op.a, registers[op.b]);

        }

        public static void setRegisters(int[] registers){
            Command.registers = registers;
        }
        IntBinaryOperator function;
        ToIntFunction<Operation> opcode;

        Command(IntBinaryOperator function, ToIntFunction<Operation> opcode) {
            this.function = function;
            this.opcode = opcode;
        }
    }
}
