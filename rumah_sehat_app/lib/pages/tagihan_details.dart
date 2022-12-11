import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/providers/tagihan.dart';

class TagihanDetails extends StatefulWidget {
  static const route = "/tagihan/detail";

  const TagihanDetails({Key? key}) : super(key: key);

  @override
  State<TagihanDetails> createState() => _TagihanDetailsState();
}

class _TagihanDetailsState extends State<TagihanDetails> {
  bool isInit = true;
  bool isLoading = false;

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
              height: 200,
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
                          child: const Text("Dokter",
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
                          child: const Text("Waktu",
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


            const SizedBox(height: 20),
            /// Pay Button
            Container(
              width: double.infinity,
              margin: const EdgeInsets.only(bottom: 30),
              child:
                selectedTagihan.isPaid != true ?
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
                  )
                    : Container()
              ),
          ],
        ),
    );
  }
}