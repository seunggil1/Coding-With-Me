export const compileLang = [
	{
		language: 'java',
		name: 'java',
		code: 'import java.util.*;\nimport java.io.*;\n\npublic class Main{\n    public static void main(String[] args) throws IOException {\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\n       \n        int a = Integer.parseInt(re.readLine());\n        int b = Integer.parseInt(re.readLine());\n\n        System.out.println(a+b);\n        re.close();\n    }\n}',
	},
	{
		language: 'py',
		name: 'python',
		code: 'a=input()\nb=input()\nprint(a+b)',
	},
	{
		language: 'js',
		name: 'javascript',
		code: "var input = require('fs').readFileSync('/dev/stdin').toString().split(' ');\n\na = parseInt(input[0]);\nb = parseInt(input[1]);\nconsole.log(a+b);",
	},
	{
		language: 'cpp',
		name: 'c++',
		code: '#include <iostream>\n\nusing namespace std;\n\nint main(void){\n    int a, b;\n    cin >> a >> b;\n    cout << a + b;\n    return 0;\n}',
	},
	{
		language: 'c',
		name: 'c',
		code: '#include <stdio.h>\n\nint main(){\n    int a,b;\n    scanf("%d",&a);\n    scanf("%d",&b);\n    printf("%d",a+b);\n    return 0;\n}',
	},
];
