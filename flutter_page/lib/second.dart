import 'package:flutter/material.dart';

class SecondApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Second Page',
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: SecondPage(title: "SecondPage Standalone Engine"),
    );
  }
}

class SecondPage extends StatefulWidget {
  SecondPage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  SecondPageState createState() => SecondPageState();
}

final searchBarPage = SearchBarPage();

class SecondPageState extends State<SecondPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
          preferredSize: Size(double.infinity, 56), //  is the height
          child: AppBar(backgroundColor: Colors.amber, title: searchBarPage)),
    );
  }
}

class SearchBarPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _SearchBarPageState();
}

final TextEditingController controller = TextEditingController();
var that;
var loading;

class _SearchBarPageState extends State<SearchBarPage> {
  @override
  void initState() {
    super.initState();
    that = this;
    loading = false;
  }

  String oldKey;

  void onSearchTextChanged(String text) {
    print('onSearchTextChanged:$text');
  }

  @override
  void dispose() {
    print('dispose');
    super.dispose();
  }

  Widget build(BuildContext context) {
    return Container(
      color: Colors.amber,
      child: Padding(
        padding: const EdgeInsets.only(top: 5.0, bottom: 5.0),
        child: Card(
          child: Container(
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                SizedBox(width: 2.0),
                Icon(
                  Icons.search,
                  color: Colors.grey,
                  size: 18.0,
                ),
                Expanded(
                  child: Container(
                    alignment: Alignment.center,
                    child: TextField(
                      controller: controller,
                      decoration: InputDecoration(
                          contentPadding: EdgeInsets.only(top: 0.0),
                          hintText: '输入关键词搜索',
                          hintStyle: TextStyle(fontSize: 12.0),
                          border: InputBorder.none),
                      onChanged: onSearchTextChanged,
                    ),
                  ),
                ),
                IconButton(
                  icon: Icon(Icons.cancel),
                  color: Colors.grey,
                  iconSize: 18.0,
                  onPressed: () {
                    controller.clear();
                    // onSearchTextChanged('');
                  },
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
