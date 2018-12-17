package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;
import com.scottejames.util.FileHelper;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntFunction;

public class Day16Classification extends AocDay {
    List<Sample> samples;
    List<int[]> program;
    Map<Integer, Set<Command>> commandMap = new HashMap<>();
    // 0 ; GTRR
    // 1 : BORR
    // 2 : GTIR
    // 3 : EQRI
    // 4 : ADDR
    // 5 : SETI
    // 6 : EQRR
    // 7 : GTRI
    // 8 : BANR
    // 9 : ADDI
    // 10 : SETR
    // 11 : MULR
    // 12 : BORI
    // 13 : MULI
    // 14: EQIR
    // 15: BANI

    @Override
    public void runDay() {
//        Sample s = new Sample();
//        s.before = new int[]{3, 2, 1, 1};
//        s.after = new int[]{3, 2, 2, 1};
//        s.codes = new int[]{9, 2, 1, 2};
        Set<Integer> known = new HashSet<>();
        Set<Command> knownOpCode = new HashSet<>();
        known.add(14);
        known.add(6);
        known.add(7);
        known.add(3);
        known.add(0);
        known.add(10);
        known.add(2);
        known.add(8);
        known.add(15);
        known.add(13);
        known.add(5);
        known.add(4);
        known.add(12);
        known.add(9);
        known.add(1);
        known.add(11);
        knownOpCode.add(Command.MULR);
        knownOpCode.add(Command.BORR);
        knownOpCode.add(Command.ADDI);
        knownOpCode.add(Command.BORI);
        knownOpCode.add(Command.ADDR);
        knownOpCode.add(Command.SETI);
        knownOpCode.add(Command.MULI);
        knownOpCode.add(Command.BANI);
        knownOpCode.add(Command.BANR);
        knownOpCode.add(Command.SETR);
        knownOpCode.add(Command.EQIR);
        knownOpCode.add(Command.EQRR);
        knownOpCode.add(Command.GTRI);
        knownOpCode.add(Command.GTRR);
        knownOpCode.add(Command.EQRI);
        knownOpCode.add(Command.GTIR);
        loadData();
        loadProgram();
        int gThree = 0;
        for (Sample s: samples) {
            int count = 0;
            for (Command cmd : Command.values()) {

//            System.out.println("applying " + cmd);
                int[] copy = new int[s.before.length];
                System.arraycopy(s.before, 0, copy, 0, s.before.length);
                run(copy, s.codes, cmd);
                //System.out.println(copy[0] + " " + copy[1] + " " + copy[2] + " " + copy[3]);
                if (Arrays.equals(copy, s.after)) {
                    count++;
                    commandMap.putIfAbsent(s.codes[0], new HashSet<>());
                    commandMap.get(s.codes[0]).add(cmd);

                }
            }
            if (count > 2) gThree++;

            for (Integer opCode: commandMap.keySet()){
                if (!known.contains(opCode)) {
                    commandMap.get(opCode).removeAll(knownOpCode);
                    System.out.print(" Op Code: " + opCode + " ");
                    System.out.println(commandMap.get(opCode));
                }
            }
        }
        System.out.println("Part 1: " + gThree);

        int[] register = { 0, 0, 0, 0 };
        for (int[] line : program) {
            run(register, line, Command.values()[line[0]]);
        }
        System.out.println("Part 2: " +  register[0]);


    }
    public void loadProgram(){
        List<String> items = this.getDataFromFile("2018/DaySixteenProgram_data.txt");
        program = new ArrayList<int[]>();
        for(String item: items)
            program.add(FileHelper.StringArrayToInt(item.split(" ")));

    }
    public void loadData(){
        List<String> items = this.getDataFromFile("2018/DaySixteen_data.txt");

        samples = new ArrayList<>();
        Sample sample = new Sample();
        samples.add(sample);

        for(String item: items){
            if (item.isEmpty()) {
                sample = new Sample();
                samples.add(sample);
                continue;
            }
            if (item.startsWith("Before")) {
                String[] split = item.substring(9, item.indexOf(']')).split(", ");
                sample.before = FileHelper.StringArrayToInt(split);

            } else if (item.startsWith("After")) {
                String[] split = item.substring(9, item.indexOf(']')).split(", ");
                sample.after = FileHelper.StringArrayToInt(split);

            } else {
                sample.codes = FileHelper.StringArrayToInt(item.split(" "));

            }
        }
    }
    void run(int[] registers, int[] codes, Command cmd) {
        int opCode = codes[0];
        int a = codes[1];
        int b = codes[2];
        int c = codes[3];
        Operation op = new Operation(cmd,registers,a,b,c);
        int result = cmd.opcode.applyAsInt(op);
        registers[c] = result;
    }
    class Operation {
        Command cmd;

        int[] registers;
        int a;
        int b;
        int c;

        public Operation(Command cmd,int[] registers, int a, int b, int c) {
            this.cmd = cmd;
            this.registers = registers;
            this.a = a;
            this.b = b;
            this.c = c;
        }


    }

    class Sample {
        int[] before = new int[4];
        int[] after = new int[4];
        int[] codes = new int[4];
    }
    // 0 ; GTRR
    // 1 : BORR
    // 2 : GTIR
    // 3 : EQRI
    // 4 : ADDR
    // 5 : SETI
    // 6 : EQRR

    // 7 : GTRI
    // 8 : BANR
    // 9 : ADDI
    // 10 : SETR
    // 11 : MULR
    // 12 : BORI

    // 13 : MULI
    // 14: EQIR
    // 15: BANI
    enum Command {
        GTRR((x, y) -> x > y ? 1 : 0, Command::rr),
        BORR((x, y) -> x | y, Command::rr),
        GTIR((x, y) -> x > y ? 1 : 0, Command::ir),
        EQRI((x, y) -> x == y ? 1 : 0, Command::ri),
        ADDR((x, y) -> x + y, Command::rr),
        SETI((x, y) -> x, Command::ir),
        EQRR((x, y) -> x == y ? 1 : 0, Command::rr),
        GTRI((x, y) -> x > y ? 1 : 0, Command::ri),
        BANR((x, y) -> x & y, Command::rr),
        ADDI((x, y) -> x + y, Command::ri),
        SETR((x, y) -> x, Command::rr),
        MULR((x, y) -> x * y, Command::rr),
        BORI((x, y) -> x | y, Command::ri),
        MULI((x, y) -> x * y, Command::ri),
        EQIR((x, y) -> x == y ? 1 : 0, Command::ir),
        BANI((x, y) -> x & y, Command::ri);

        public static int ri(Operation op) {
            return op.cmd.function.applyAsInt(op.registers[op.a], op.b);
        }

        public static int rr(Operation op) {
            return op.cmd.function.applyAsInt(op.registers[op.a], op.registers[op.b]);
        }

        public static int ir(Operation op) {
            return op.cmd.function.applyAsInt(op.a, op.registers[op.b]);

        }

        IntBinaryOperator function;
        ToIntFunction<Operation> opcode;

        Command(IntBinaryOperator function, ToIntFunction<Operation> opcode) {
            this.function = function;
            this.opcode = opcode;
        }
    }
}

