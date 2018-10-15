这个项目记录下如何singleTask启动下一个Fragment，并且不隐藏下一个fragment<br/>
如果想把上一个fragment透出，必须把当前fragment的背景设置为透明（@android:color/transparent）

FragmentMation使用了第三方的库：me.yokeyword:fragmentation-core
因为第三方库不太满足需求，所以使用了反射调用其方法

proguard-rules.pro<br/>
----保护反射不受混淆影响----<br/>
-keep class android.support.v4.app.FragmentManager<br/>
-keep class me.yokeyword.fragmentation.ISupportFragment<br/>
-keepclasseswithmembernames class me.yokeyword.fragmentation.TransactionDelegate {<br/>
    *;<br/>
}<br/>
----保护反射不受混淆影响----<br/>