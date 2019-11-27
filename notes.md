# Notes

### Flutter
1. 通过 ``FlutterActivity::withNewEngine`` 创建FlutterActivity，可直接指定进入路由页面。但每次Engine初始化过程有短暂黑屏，体验差。
2. 使用 **FlutterEngineCached** 存储 Engine 实例，在应用启动时预热，解决黑屏问题。
3. 通过 ``FlutterActivity::withCachedEngine`` 创建，默认Class为FlutterActivity，子类需要手动生成Intent。<br>
缓存引擎方式默认进入 **/** （首页）。如果需要进入其他页面，子类需要恰当时机调用 ``flutterEngine.getNavigationChannel().pushRoute()`` 设置。<br>
这种方法会看到Flutter首页切换至目标页面动画，且返回键首先回到Flutter模块首页。
4. 在 **FlutterEngineCached** 中存储多个 **Engine** 实例，不同的实例指定不同 **DartEntrypoint**，FlutterActivity子类指定相应 ENGINE ID 启动各自页面。