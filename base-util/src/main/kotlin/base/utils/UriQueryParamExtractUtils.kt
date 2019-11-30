@file:JvmName("UriQueryParamExtractor")

package base.utils

import com.google.common.base.Charsets.UTF_8
import org.apache.http.client.utils.URLEncodedUtils
import java.net.URI
import java.nio.charset.Charset

fun extract(uri: URI): Map<String, String> {
    return URLEncodedUtils.parse(uri, Charset.forName(UTF_8.name()))
            .map { it.name to it.value }
            .toMap()
}
