package parser

import model.AppUser

import java.util.stream.Collectors

class AppUserParser {
    List<AppUser> parse(String path) {
        File file = new File(getClass().getResource(path).toURI())

        List<String> appUserStrings = file.text.split('\r\n\r\n\r\n\r\n')

        return appUserStrings.stream()
                .map { u ->
            List<String> userData = u.split('\r\n\r\n')
            List<String> userName = userData.get(0).split(' ')

            AppUser user = new AppUser()
            user.firstName = userName.get(0)
            user.lastName = userName.get(1)
            user.email = userData.get(1)
            user.password = userData.get(2)

            return user
        }
        .collect(Collectors.toList())
    }

}
