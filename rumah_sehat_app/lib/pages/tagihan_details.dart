import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/pasien_saldo.dart';
import 'package:rumah_sehat_app/providers/tagihan.dart';

import '../providers/pasien.dart';

class TagihanDetails extends StatefulWidget {
  static const route = "/tagihan/detail";

  const TagihanDetails({Key? key}) : super(key: key);

  @override
  State<TagihanDetails> createState() => _TagihanDetailsState();
}

class _TagihanDetailsState extends State<TagihanDetails> {
  bool isInit = true;
  bool isLoading = false;

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

  /// Function to pay tagihan
  void bayar(String id) {
    Provider.of<TagihanProvider>(context, listen: false)
        .bayar(id)
        .then((value) => Navigator.pop(context)).catchError((err){
      showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: const Text("Error Occured"),
            content: Text("Error : $err"),
            actions: [
              TextButton(
                onPressed: () => Navigator.pop(context),
                child: const Text("OKAY"),
              ),
            ],
          );
        },
      );
    });
  }


  /// Flutter Page Starts Here
  @override
  Widget build(BuildContext context) {
    String idTagihan = ModalRoute.of(context)?.settings.arguments as String;
    print(idTagihan);
    var prov = Provider.of<TagihanProvider>(context, listen: false);
    var selectedTagihan = prov.selectById(idTagihan);
    var provPasien = Provider.of<PasienProvider>(context);


    return Scaffold(
      /// Application Header (AppBar)
      appBar: AppBar(
        title: const Text("Tagihan Details"),
        elevation: 0.0,
        backgroundColor: Colors.blue,
        centerTitle: true,
        leading: GestureDetector(
          onTap: () {
            Navigator.pop(context);
          },
          child: const Icon(
            Icons.arrow_back,
            color: Colors.white,
          ),
        ),
      ),

      /// Application Body (body)
      body:

        /// if isLoading = True, then show CircularProgessIndicator
        (isLoading) ? const Center(
          child: CircularProgressIndicator(),
        )

        /// else show Tagihan Detail
        : Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Container(
              height: 300,
              decoration: const BoxDecoration(
                  color: Colors.blue,
                  borderRadius: BorderRadius.only(bottomLeft: Radius.circular(30), bottomRight: Radius.circular(30))
              ),
              child: Container(
                margin: const EdgeInsets.only(bottom: 20),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Container(
                          margin: const EdgeInsets.only(top: 20),
                          child: const Text("Kode",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.w700,
                            ),
                          ),
                        ),
                        Container(
                          margin: const EdgeInsets.only(top: 20),
                          child: const Text("Tgl. Appointment",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.w700,
                            ),
                          ),
                        ),
                        Container(
                          margin: const EdgeInsets.only(top: 20),
                          child: const Text("Tgl. Bayar",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.w700,
                            ),
                          ),
                        ),
                        Container(
                          margin: const EdgeInsets.only(top: 20),
                          child: const Text("Harga Total",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.w700,
                            ),
                          ),
                        ),
                        Container(
                          margin: const EdgeInsets.only(top: 20),
                          child: const Text("Status",
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
                    Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Container(
                          margin: const EdgeInsets.only(top: 20, left: 40),
                          child: Text(selectedTagihan.id.toString(),
                            style: const TextStyle(
                              color: Colors.white,
                              fontSize: 22,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.w700,
                            ),
                          ),
                        ),
                        Container(
                          margin: const EdgeInsets.only(top: 20, left: 40),
                          child: Text(selectedTagihan.tanggalTerbuat.toString(),
                            style: const TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        Container(
                          margin: const EdgeInsets.only(top: 20, left: 40),
                          child: Text(selectedTagihan.tanggalBayar.toString(),
                          style: const TextStyle(
                            color: Colors.white,
                            fontSize: 20,
                            fontFamily: 'Roboto',
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                        Container(
                          margin: const EdgeInsets.only(top: 20, left: 40),
                          child: Text(selectedTagihan.total.toString(),
                            style: const TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        Container(
                          margin: const EdgeInsets.only(top: 20, left: 40),
                          child: Text(selectedTagihan.isPaid == true? "SUDAH DIBAYAR" : "BELUM DIBAYAR" ,
                            style: TextStyle(
                              color: selectedTagihan.isPaid == true? Colors.lightGreen : Colors.amber,
                              fontSize: 20,
                              fontFamily: 'Roboto',
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ],
                    )
                  ],
                ),
              ),
            ),

            Container(
              margin: const EdgeInsets.only(left: 20, top: 30),
              child: const Text('Saldo anda sekarang: ',
                style: TextStyle(
                  color: Color(0xff363636),
                  fontSize: 25,
                  fontFamily: 'Roboto',
                  fontWeight: FontWeight.w700,
                ),
              ),
            ),
            Container(
              color: Colors.blue,
              child:
              ListTile(
                title: Text(provPasien.getPasien[0].saldo.toString(),
                  style: const TextStyle(
                    fontSize: 35,
                    fontWeight: FontWeight.bold,
                    color: Colors.white,
                  ),
                ),
              ),
            ),


            const SizedBox(height: 20),
            /// Pay Button
            Container(
              width: double.infinity,
              margin: const EdgeInsets.only(bottom: 30),
              child:
                selectedTagihan.isPaid != true ?
                  selectedTagihan.total! <= provPasien.getPasien[0].saldo ?

                  ElevatedButton(
                    onPressed: () => bayar(selectedTagihan.id.toString()),
                    child: const Text("BAYAR",
                      style: TextStyle(
                        fontSize: 18,
                      ),
                    ),
                    style: ElevatedButton.styleFrom(
                      padding: const EdgeInsets.symmetric(vertical: 15),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(100),
                      ),
                    ),
                  ) :
                  ElevatedButton(
                    onPressed: () =>
                        Navigator.pushNamed(context, PasienSaldo.route),
                    child: const Text("ISI SALDO",
                      style: TextStyle(
                        fontSize: 18,
                      ),
                    ),
                    style: ElevatedButton.styleFrom(
                      padding: const EdgeInsets.symmetric(vertical: 15),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(100),
                      ),
                    ),
                  )

                    : Container()
              ),
          ],
        ),
    );
  }
}