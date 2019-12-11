# NGD 训练集与测试集划分工具

## 使用方式
### 1、将源码打包jar包（如果已有jar包可以忽略）
### 2、将待划分的标准意图导入文件重命名为 in.xlsx
### 3、确保 in.xlsx与jar包在同一目录下
### 4.1、在jar包目录下，执行命令 java -jar divide.jar （二选一）
### 4.2、在jar包目录下，双击run.bat （二选一） 
(注，4.1与4.2任选一个，且divide.jar 为打包好的jar包名称（在bat文件已写死），执行时替换为自己的jar包名称)
### 5、打开新生成的out1.xlsx与out9.xlsx 进行验证
（注：out1.xlsx 为测试集，out9.xlsx 为训练集）


## 运行环境
需要java8及以上，验证方式： java -version

