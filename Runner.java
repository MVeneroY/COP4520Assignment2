class Runner {
    public static void main(String[] args) {
        int n = 0;
        if (args.length == 1) n = Integer.parseInt(args[0]);

        System.out.println(n + " threads started");
    }
}