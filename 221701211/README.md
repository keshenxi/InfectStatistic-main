一个疫情统计程序

如何运行:

命令行（win+r cmd）cd到项目src下，之后输入命令：

$ java InfectStatistic list -date 2020-01-22 -log D:/log/ -out D:/output.txt<br>
会读取D:/log/下的所有日志，然后处理日志和命令，在D盘下生成ouput.txt文件列出2020-01-22全国和所有省的情况（全国总是排第一个，别的省按拼音先后排序）

全国 感染患者22人 疑似患者25人 治愈10人 死亡2人<br>
福建 感染患者2人 疑似患者5人 治愈0人 死亡0人<br>
浙江 感染患者3人 疑似患者5人 治愈2人 死亡1人<br>
// 该文档并非真实数据，仅供测试使用<br>
list命令 支持以下命令行参数：<br>

-log 指定日志目录的位置，该项必会附带，请直接使用传入的路径，而不是自己设置路径 <br>
-out 指定输出文件路径和文件名，该项必会附带，请直接使用传入的路径，而不是自己设置路径 <br>
-date 指定日期，不设置则默认为所提供日志最新的一天。你需要确保你处理了指定日期之前的所有log文件 <br>
-type 可选择[ip： infection patients 感染患者，sp： suspected patients 疑似患者，cure：治愈 ，dead：死亡患者]，使用缩写选择，如 -type ip 表示只列出感染患者的情况，-type sp cure则会按顺序【sp, cure】列出疑似患者和治愈患者的情况，不指定该项默认会列出所有情况。 <br>
-province 指定列出的省，如-province 福建，则只列出福建，-province 全国 浙江则只会列出全国、浙江 <br>
注：java InfectStatistic表示执行主类InfectStatistic，list为命令，-date代表该命令附带的参数，-date后边跟着具体的参数值，如2020-01-22。-type 的多个参数值会用空格分离，每个命令参数都在上方给出了描述，每个命令都会携带一到多个命令参数<br>

功能简介:能够列出全国和各省在某日的感染情况<br>
如：<br>
福建 新增 感染患者 23人<br>
福建 新增 疑似患者 2人<br>
浙江 感染患者 流入 福建 12人<br>
湖北 疑似患者 流入 福建 2人<br>
安徽 死亡 2人<br>
新疆 治愈 3人<br>
福建 疑似患者 确诊感染 2人<br>
新疆 排除 疑似患者 5人<br>
// 该文档并非真实数据，仅供测试使用<br>

作业地址：https://edu.cnblogs.com/campus/fzu/2020SpringW/homework/10281

博客链接：https://www.cnblogs.com/nixige/p/12309641.html


