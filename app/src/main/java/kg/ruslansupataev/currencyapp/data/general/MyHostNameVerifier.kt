package kg.ruslansupataev.currencyapp.data.general

import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 * Simple verification of host
 */
class MyHostnameVerifier : HostnameVerifier {
    override fun verify(hostname: String?, session: SSLSession?): Boolean {
        if(hostname.isNullOrEmpty()) return false

        //Check if the hostname passed to the `verify` method is exactly equal to a trusted hostname
        val trustedHostname = "api.exchangerate.host"
        if (hostname == trustedHostname) return true

        //Check if the certificate is issued by a trusted public CA.
        //It's important to keep in mind that you should always check the certificate's issuerDN,
        //whether you're using a self-signed certificate or not
        val x509Certificate = session?.peerCertificates?.first() as X509Certificate
        if(!x509Certificate.issuerDN.name.contains("Let's Encrypt")) return false

        //Check if the hostname passed to the `verify` method is matching the regular expression
        val pattern = Regex("^(api|secure)\\.exchangerate\\.host$")
        return pattern.matches(hostname)
    }
}