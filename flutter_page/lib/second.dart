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

  SecondPage({Key key, this.title}): super(key: key);

  final String title;

  @override
  SecondPageState createState() => SecondPageState();

}

class SecondPageState extends State<SecondPage> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(

        child: Column(

          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Hello world second page !!!',
            ),
          ],
        ),
      ),
    );
  }
}