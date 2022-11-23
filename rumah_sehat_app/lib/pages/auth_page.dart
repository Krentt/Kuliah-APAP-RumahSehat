import 'package:flutter/material.dart';
import 'package:flutter_login/flutter_login.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/home_page.dart';
import 'package:rumah_sehat_app/providers/auth.dart';

const users = const {
  'dribbble@gmail.com': '12345',
  'hunter@gmail.com': 'hunter',
};
class LoginScreen extends StatefulWidget{
  @override
  _LoginScreen createState() => _LoginScreen();
}

class _LoginScreen extends State<LoginScreen> {
  Duration get loginTime => Duration(milliseconds: 2250);

  Future<String?> _authUser(LoginData data) {
    debugPrint('Name: ${data.name}, Password: ${data.password}');
    return Future.delayed(loginTime).then((_) async {
      try{
        await Provider.of<Authh>(context, listen: false).login(data.name, data.password);
      } catch (err){
        return "Password atau Username Salah!";
      }
      return null;
    });
  }

  Future<String?> _signupUser(SignupData data) {
    debugPrint('Signup Name: ${data.name}, '
        'Password: ${data.password}, '
        'Email: ${data.additionalSignupData!["email"]},'
        'Umur: ${data.additionalSignupData!["umur"]},');
    return Future.delayed(loginTime).then((_) async {
      try{
        await Provider.of<Authh>(context, listen: false).signup(data.name, data.password, data.additionalSignupData!["email"], data.additionalSignupData!["umur"], data.additionalSignupData!["nama"]);
      } catch (err){
        return "Sign Up Gagal!";
      }
      return null;
    });
  }

  Future<String?> _recoverPassword(String name) {
    debugPrint('Name: $name');
    return Future.delayed(loginTime).then((_) {
      if (!users.containsKey(name)) {
        return 'User not exists';
      }
      return null;
    });
  }


  @override
  Widget build(BuildContext context) {
    return FlutterLogin(
      title: 'Rumah Sehat',
      onLogin: _authUser,
      onSignup: _signupUser,
      userType: LoginUserType.name,
      userValidator: (value) {
        if (value == null){
          return "Tidak boleh kosong!";
        }
        return null;
      },
      additionalSignupFields: [
        const UserFormField(
          keyName: 'email',
          icon: Icon(Icons.email),
        ),
        const UserFormField(
            keyName: 'nama'),
        UserFormField(
          keyName: 'umur',
          displayName: 'Umur',
          userType: LoginUserType.phone,
          icon: Icon(Icons.accessible),
          fieldValidator: (value) {
            if (value == null){
              return "tidak boleh kosong";
            }
            return null;
          },
        ),
      ],
      onSubmitAnimationCompleted: () {
        // Navigator.of(context).pushReplacement(MaterialPageRoute(
        //   builder: (context) => HomePage(),
        // ));
        Provider.of<Authh>(context, listen: false).tempData();
      },
      onRecoverPassword: _recoverPassword,
    );
  }
}