import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/pasien_saldo.dart';
import 'package:rumah_sehat_app/providers/pasien.dart';
import 'package:rumah_sehat_app/widgets/pasien_widget.dart';


class PasienProfile extends StatefulWidget {
  static const route = "/pasien/profile";

  const PasienProfile({Key? key}) : super(key: key);

  @override
  _PasienProfileState createState() => _PasienProfileState();
}

class _PasienProfileState extends State<PasienProfile> {
  bool isInit = true;
  bool isLoading = false;

  final TextEditingController textEditingController = TextEditingController();


  @override
  void didChangeDependencies() {
    if (isInit) {
      isLoading = true;
      Provider.of<PasienProvider>(context, listen: false).getPasienProfile().then((
          value) {
        setState(() {
          isLoading = false;
        });
      }).catchError(
            (err) {
          print(err);
          showDialog(
            context: context,
            builder: (context) {
              return AlertDialog(
                title: const Text("Error Occured"),
                content: Text(err.toString()),
                actions: [
                  TextButton(
                    onPressed: () {
                      setState(() {
                        isLoading = false;
                      });
                      Navigator.pop(context);
                    },
                    child: const Text("Okay"),
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

  /// Flutter Page Starts Here
  @override
  Widget build(BuildContext context) {
    final prov = Provider.of<PasienProvider>(context);
    print(prov);
    print(prov.getPasien);
    return Scaffold(

      /// Application Header (AppBar)
      appBar: AppBar(
        title: const Text("Your Profile"),
        actions: [
          /// Button Links to Saldo Update Page
          IconButton(
            icon: const Icon(Icons.add),
            onPressed: () =>
                Navigator.pushNamed(context, PasienSaldo.route),
          ),
        ],
      ),

      /// Application Body (body)
      body: (isLoading) ?
        /// if isLoading = True, then show CircularProgessIndicator
        const Center(
          child: CircularProgressIndicator(),
        )

        /// if isLoading = False, then show Pasien Profile
        : (prov.getPasien.isEmpty) ?
          /// if prov.getPasien.isEmpty, then shows "No Data"
          const Center(
            child: Text(
              "No Data",
              style: TextStyle(
                fontSize: 25,
              ),
            ),
          )

          /// if prov.getPasien.isNotEmpty, then Build Pasien Information
          : ListView(
            children: <Widget>[
            Container(
            height: 250,
            decoration: BoxDecoration(
              gradient: LinearGradient(
                colors: [Colors.blue, Colors.deepPurple.shade300],
                begin: Alignment.centerLeft,
                end: Alignment.centerRight,
                stops: const [0.2, 0.85],
              ),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                ListTile(
                  title: Text(prov.getPasien[0].nama,
                    style: const TextStyle(
                      fontSize: 35,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                ),
                ListTile(
                  title: Text(prov.getPasien[0].username,
                    style: const TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                ),
              ],
            ),
          ),
            Row(
              children: <Widget>[
                Expanded(
                  child: Container(
                    color: Colors.blue.shade300,
                    child: const ListTile(
                      title: Text(
                        'Saldo',
                        textAlign: TextAlign.center,
                        style: TextStyle(
                          fontWeight: FontWeight.bold,
                          fontSize: 30,
                          color: Colors.white,
                        ),
                      ),
                    ),
                  ),
                ),
                Expanded(
                  child: Container(
                    color: Colors.blue,
                    child:
                    ListTile(
                      title: Text(prov.getPasien[0].saldo.toString(),
                        style: const TextStyle(
                          fontSize: 35,
                          fontWeight: FontWeight.bold,
                          color: Colors.white,
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            ),
            Container(
              child: Column(
                children: <Widget>[
                  ListTile(
                    title: const Text("Email: ",
                      style: TextStyle(
                        fontSize: 15,
                        fontWeight: FontWeight.bold,
                        color: Colors.black,
                      ),
                    ),
                      subtitle: Text(prov.getPasien[0].email)
                  ),
                  const Divider(),
                  ListTile(
                    title: const Text("Umur: ",
                      style: TextStyle(
                        fontSize: 15,
                        fontWeight: FontWeight.bold,
                        color: Colors.black,
                      ),
                    ),
                      subtitle: Text(prov.getPasien[0].umur.toString())
                  ),
                ],
              ),
            )
          ],
        )

        /// (other style) Builds List of Pasien Information
        // : ListView.builder(
        // itemBuilder: (context, i) => PasienWidget(
        //   prov.getPasien[0].nama,
        //   prov.getPasien[0].username,
        //   prov.getPasien[0].email,
        //   prov.getPasien[0].saldo,
        //   prov.getPasien[0].umur,
        //   ),
        // ),
    );
  }
}