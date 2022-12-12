import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/resep.dart';

class ObatItem extends StatelessWidget {
  final String? nama, kuantitas;

  ObatItem(this.nama, this.kuantitas);

  @override
  Widget build(BuildContext context) {
    var prov = Provider.of<Reseps>(context, listen: false);
    return ListTile(
      leading: const CircleAvatar(
        child: Padding(
          padding: EdgeInsets.all(5),
          child: FittedBox(
            child: Icon(Icons.medical_information),
          ),
        ),
      ),
      title: Text("$nama", style: TextStyle(
        color: Colors.white,
        fontWeight: FontWeight.bold
      ),),
      subtitle: Text("Kuantitas : $kuantitas", style: TextStyle(
        color: Colors.white
      ),),
    );
  }
}