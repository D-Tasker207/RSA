<h1>Implementation of RSA public/private key encryption in Java</h1>

<h3>File Breakdown</h3>

<ul>

<li><b>RSA.java:</b> Main class containing the encryption and decryption methods, as well as key generation methods</li>

<li><b>KeyPair.java:</b>  Utility class for storing the private/public key pair</li>

<li><b>RSAKey.java:</b> Utility class for storing the key properties</li>

<li><b>GCD.java:</b> Utility class for storing the key properties</li>

<li><b>OAEP.java:</b> Implementation of Optimal Asymmetric Encryption Padding (OAEP) <b>~~Currently Non-functional~~</b></li>

</ul>

<h3>How to Use</h3>

<h4>Standalone:</h4>
    Run the main() in RSA.java, enter text into the terminal prompt to see both encrypted and decrypted versions, and keys used.

<h4>As Package:</h4>
<ul>
<li><b>To Encrypt:</b> Create a key set using generateKeys() (either pass in two prime numbers, or pass no params to use random 2048 bit primes), then call encrypt() in RSA.java with the parameters: String (utf-8) message and the RSAKey instance of the key being used to encrypt. Returns a BigInteger representing the encoded message.</li>

<li><b>To Decrypt:</b> Call decrypt() function in RSA.java, with parameters: BigInteger representing the encoded message, and RSAKey for the decryption key to be used. Function returns decrypted text as a utf-8 encoded String.</li>
</ul>