import 'package:flutter/material.dart';
import '../models/chat_model.dart';

class ChatScreen extends StatefulWidget {
  @override
  ChatScreenState createState() {
    return new ChatScreenState();
  }
}

class ChatScreenState extends State<ChatScreen> {
  @override
  Widget build(BuildContext context) {
    return new ListView.builder(
        itemCount: getItemCount(),
        itemBuilder: (context, i) => onCreateWidget(context, i));
  }

  int getItemCount() {
    return dummyData.length;
  }

  Widget onCreateWidget(BuildContext context, int position) {
    return new Column(
      children: <Widget>[
        new Divider(
          height: 10.0,
        ),
        new ListTile(
          leading: new CircleAvatar(
            foregroundColor: Theme.of(context).primaryColor,
            backgroundColor: Colors.grey,
            backgroundImage: new NetworkImage(dummyData[position].avatarUrl),
          ),
          title: new Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              new Text(
                dummyData[position].name,
                style: new TextStyle(fontWeight: FontWeight.bold),
              ),
              new Text(
                dummyData[position].time,
                style: new TextStyle(color: Colors.grey, fontSize: 14.0),
              ),
            ],
          ),
          subtitle: new Container(
            padding: const EdgeInsets.only(top: 5.0),
            child: new Text(
              dummyData[position].message,
              style: new TextStyle(color: Colors.grey, fontSize: 15.0),
            ),
          ),
        ),
      ],
    );
  }
}
