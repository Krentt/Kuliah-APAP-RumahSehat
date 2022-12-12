import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/providers/resep.dart';
import 'package:rumah_sehat_app/widgets/obats_item.dart';

class DetailResepPage extends StatefulWidget {
  static const route = "/detail-resep";

  @override
  State<DetailResepPage> createState() => _DetailResepPageState();
}

class _DetailResepPageState extends State<DetailResepPage> {

  bool isInit = true;
  bool isLoading = false;

  @override
  void didChangeDependencies() {
    String kodeResep = ModalRoute.of(context)?.settings.arguments as String;
    if (isInit) {
      Provider.of<Reseps>(context, listen: false).inisialData(kodeResep).then((value) {
        setState(() {
          isLoading = true;
        });
      }).catchError(
            (err) {
          print(err);
          showDialog(
            context: context,
            builder: (context) {
              return AlertDialog(
                title: Text("Error Occured"),
                content: Text(err.toString()),
                actions: [
                  TextButton(
                    onPressed: () {
                      setState(() {
                        isLoading = false;
                      });
                      Navigator.pop(context);
                    },
                    child: Text("Okay"),
                  ),
                ],
              );
            },
          );
        },
      );
    }
    super.didChangeDependencies();
  }

  @override
  Widget build(BuildContext context) {
    var prov = Provider.of<Reseps>(context, listen: false);

    return (isLoading)? Scaffold(
      appBar: AppBar(
        title: Text("Detail Resep"),
        elevation: 0.0,
        backgroundColor: Colors.blue,
        centerTitle: true,
        leading: GestureDetector(
          onTap: () {
            Navigator.pop(context);
          },
          child: Icon(
            Icons.arrow_back,
            color: Colors.white,
          ),
        ),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            height: 200,
            decoration: BoxDecoration(
                color: Colors.blue,
                borderRadius: BorderRadius.only(bottomLeft: Radius.circular(30), bottomRight: Radius.circular(30))
            ),
            child: Container(
              margin: EdgeInsets.only(bottom: 20),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Container(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Container(
                          margin: EdgeInsets.only(top: 20),
                          child: Text("Kode",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.w700,
                            ),
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.only(top: 20),
                          child: Text("Dokter",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.w700,
                            ),
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.only(top: 20),
                          child: Text("Pasien",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.w700,
                            ),
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.only(top: 20),
                          child: Text("Status",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.w700,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                  Container(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Container(
                          margin: EdgeInsets.only(top: 20, left: 40),
                          child: Text(prov.id.toString(),
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 22,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.w700,
                            ),
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.only(top: 20, left: 40),
                          child: Text(prov.namaDokter.toString().toUpperCase(),
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.only(top: 20, left: 40),
                          child: Text(prov.namaPasien.toString().toUpperCase(),
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.only(top: 20, left: 40),
                          child: Text(prov.isDone == true? "TERKONFIRMASI" : "BELUM DIKONFIRMASI" ,
                            style: TextStyle(
                              color: prov.isDone == true? Colors.lightGreen : Colors.amber,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ],
                    ),
                  )
                ],
              ),
            ),
          ),
          SizedBox(height: 30),
          Container(
            margin: EdgeInsets.only(left: 20, top: 10, right: 20),
            child: Text('DAFTAR OBAT',
              style: TextStyle(
                color: Color(0xff363636),
                fontSize: 23,
                fontFamily: 'Roboto',
                fontWeight: FontWeight.bold
              ),
              textAlign: TextAlign.center,
            ),
          ),
          Card(
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15.0),
            ),
            color: Colors.blue,
            elevation: 10,
            child:
                SizedBox(
                  child: ListView.builder(
                    shrinkWrap: true,
                    itemCount: prov.allObats.length,
                    itemBuilder: (context, i) => ObatItem(
                      prov.allObats[i].nama,
                      prov.allObats[i].kuantitas.toString()
                    )),
            )
          ),
          SizedBox(height: 20),
          (prov.namaApoteker != null)?
          Card(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(15.0),
              ),
              color: Colors.blue,
              elevation: 10,
              child:
              SizedBox(
                child:
                  ListTile(
                    leading: const CircleAvatar(
                      child: Padding(
                        padding: EdgeInsets.all(5),
                        child: FittedBox(
                          child: Icon(Icons.person),
                        ),
                      ),
                    ),
                    title: Text(prov.namaApoteker.toString(), style: TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.bold
                    ),),
                    subtitle: Text("Confirmed By Apoteker", style: TextStyle(
                      color: Colors.white
                    ),),
                  )
              )
          ) :
          Container()
        ],
      ),
    ) : CircularProgressIndicator();
  }
}