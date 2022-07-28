<%-- 
    Document   : resetchangepassword
    Created on : Jun 4, 2022, 8:56:12 AM
    Author     : Luu Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--cds-jss-injection-point-->
        <link
            rel="preconnect"
            href="https://d3njjcbhbojbot.cloudfront.net"
            crossorigin=""
            />
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=IE7" />
        <meta charset="utf-8" />
        <meta property="og:site_name" content="Coursera" />
        <meta property="fb:admins" content="727836538,4807654" />
        <meta property="fb:app_id" content="823425307723964" />
        <meta name="twitter:site" content="Coursera" />
        <meta name="twitter:app:name:iphone" content="Coursera" />
        <meta name="twitter:app:name:ipad" content="Coursera" />
        <meta name="twitter:app:name:googleplay" content="Coursera" />
        <meta name="twitter:app:id:iphone" content="id736535961" />
        <meta name="twitter:app:id:ipad" content="id736535961" />
        <meta name="twitter:app:id:googleplay" content="org.coursera.android" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="apple-touch-icon"
            sizes="57x57"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/apple-touch-icon-v2-57x57.png"
            />
        <link
            rel="apple-touch-icon"
            sizes="60x60"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/apple-touch-icon-v2-60x60.png"
            />
        <link
            rel="apple-touch-icon"
            sizes="72x72"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/apple-touch-icon-v2-72x72.png"
            />
        <link
            rel="apple-touch-icon"
            sizes="76x76"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/apple-touch-icon-v2-76x76.png"
            />
        <link
            rel="apple-touch-icon"
            sizes="114x114"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/apple-touch-icon-v2-114x114.png"
            />
        <link
            rel="apple-touch-icon"
            sizes="120x120"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/apple-touch-icon-v2-120x120.png"
            />
        <link
            rel="apple-touch-icon"
            sizes="144x144"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/apple-touch-icon-v2-144x144.png"
            />
        <link
            rel="apple-touch-icon"
            sizes="152x152"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/apple-touch-icon-v2-152x152.png"
            />
        <link
            rel="apple-touch-icon"
            sizes="180x180"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/apple-touch-icon-v2-180x180.png"
            />
        <link
            rel="icon"
            type="image/png"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/favicon-v2-194x194.png"
            sizes="194x194"
            />
        <link
            rel="icon"
            type="image/png"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/android-chrome-v2-192x192.png"
            sizes="192x192"
            />
        <link
            rel="icon"
            type="image/png"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/favicon-v2-96x96.png"
            sizes="96x96"
            />
        <link
            rel="icon"
            type="image/png"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/favicon-v2-16x16.png"
            sizes="16x16"
            />
        <link
            rel="icon"
            type="image/png"
            href="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/favicon-v2-32x32.png"
            sizes="32x32"
            />
        <meta name="msapplication-TileColor" content="#2d89ef" />
        <meta
            name="msapplication-TileImage"
            content="https://d3njjcbhbojbot.cloudfront.net/web/images/favicons/mstile-v2-144x144.png"
            />
        <meta name="theme-color" content="#4689c6" />
        <meta property="qc:admins" content="366737676376375235216727" />
        <!-- Verification for Yandex-->
        <meta property="yandex-verification" content="4970cfdb825622c7" />
        <style>
            @font-face {
                font-family: "coursera-iconfont";
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/bundles/styleguide/icons/fonts/coursera.v26.eot");
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/bundles/styleguide/icons/fonts/coursera.v26.eot?#iefix")
                    format("embedded-opentype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/bundles/styleguide/icons/fonts/coursera.v26.woff")
                    format("woff"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/bundles/styleguide/icons/fonts/coursera.v26.ttf")
                    format("truetype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/bundles/styleguide/icons/fonts/coursera.v26.svg")
                    format("svg");
            }

            @font-face {
                font-family: "OpenSans-Light";
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Light.eot");
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Light.eot?#iefix")
                    format("embedded-opentype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans-v17-latin-latinext-cyrillic/opensans-300.woff2")
                    format("woff2"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans-v17-latin-latinext-cyrillic/opensans-300.woff")
                    format("woff"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Light.ttf")
                    format("truetype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Light.svg#OpenSans-Light")
                    format("svg");
                font-weight: normal;
                font-style: normal;
            }
            @font-face {
                font-family: "OpenSans";
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Regular.eot");
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Regular.eot?#iefix")
                    format("embedded-opentype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans-v17-latin-latinext-cyrillic/opensans-regular.woff2")
                    format("woff2"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans-v17-latin-latinext-cyrillic/opensans-regular.woff")
                    format("woff"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Regular.ttf")
                    format("truetype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Regular.svg#OpenSans-Regular")
                    format("svg");
                font-weight: normal;
                font-style: normal;
            }
            @font-face {
                font-family: "OpenSans-Semibold";
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Semibold.eot");
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Semibold.eot?#iefix")
                    format("embedded-opentype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans-v17-latin-latinext-cyrillic/opensans-600.woff2")
                    format("woff2"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans-v17-latin-latinext-cyrillic/opensans-600.woff")
                    format("woff"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Semibold.ttf")
                    format("truetype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Semibold.svg#OpenSans-Semibold")
                    format("svg");
                font-weight: normal;
                font-style: normal;
            }
            @font-face {
                font-family: "OpenSans";
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Bold.eot");
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Bold.eot?#iefix")
                    format("embedded-opentype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans-v17-latin-latinext-cyrillic/opensans-700.woff2")
                    format("woff2"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans-v17-latin-latinext-cyrillic/opensans-700.woff")
                    format("woff"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Bold.ttf")
                    format("truetype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/opensans/OpenSans-Bold.svg#OpenSans-Bold")
                    format("svg");
                font-weight: bold;
                font-style: normal;
            }
            @font-face {
                font-family: "Merriweather";
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Regular.eot");
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Regular.eot?#iefix")
                    format("embedded-opentype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Regular.woff2")
                    format("woff2"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Regular.woff")
                    format("woff"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Regular.ttf")
                    format("truetype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Regular.svg#Merriweather-Regular")
                    format("svg");
                font-weight: normal;
                font-style: normal;
            }
            @font-face {
                font-family: "Merriweather-Light";
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Light.eot");
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Light.eot?#iefix")
                    format("embedded-opentype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Light.woff2")
                    format("woff2"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Light.woff")
                    format("woff"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Light.ttf")
                    format("truetype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/merriweather/Merriweather-Regular.svg#Merriweather-Light")
                    format("svg");
                font-weight: normal;
                font-style: normal;
            }
            @font-face {
                font-family: "Source Sans Pro";
                font-style: normal;
                font-weight: 300;
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-300.eot"); /* IE9 Compat Modes */
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-300.eot?#iefix")
                    format("embedded-opentype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-300.woff2")
                    format("woff2"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-300.woff")
                    format("woff"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-300.ttf")
                    format("truetype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-300.svg#SourceSansPro")
                    format("svg");
            }
            @font-face {
                font-family: "Source Sans Pro";
                font-style: normal;
                font-weight: 400;
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-regular.eot"); /* IE9 Compat Modes */
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-regular.eot?#iefix")
                    format("embedded-opentype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-regular.woff2")
                    format("woff2"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-regular.woff")
                    format("woff"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-regular.ttf")
                    format("truetype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-regular.svg#SourceSansPro")
                    format("svg");
            }
            @font-face {
                font-family: "Source Sans Pro";
                font-style: normal;
                font-weight: 600;
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-600.eot"); /* IE9 Compat Modes */
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-600.eot?#iefix")
                    format("embedded-opentype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-600.woff2")
                    format("woff2"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-600.woff")
                    format("woff"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-600.ttf")
                    format("truetype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-600.svg#SourceSansPro")
                    format("svg");
            }
            @font-face {
                font-family: "Source Sans Pro";
                font-style: normal;
                font-weight: bold;
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-700.eot");
                src: url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-700.eot?#iefix")
                    format("embedded-opentype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-700.woff2")
                    format("woff2"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-700.woff")
                    format("woff"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-700.ttf")
                    format("truetype"),
                    url("https://d3njjcbhbojbot.cloudfront.net/web/type/source-sans-pro-v14-latin/source-sans-pro-v14-latin-700.svg#SourceSansPro")
                    format("svg");
            }
        </style>
        <script>
            // NOTE: This file gets included in Jade pre-transformed so it must be ES5-safe.

            (function (global, factory) {
                global.errorTracker = factory();
            })(this, function () {
                var lastError = {};

                function errorEquals(left, right) {
                    return ["message", "url", "line", "column"].every(function (field) {
                        return left[field] == right[field];
                    });
                }

                return function (tracker, options) {
                    options = options || {};

                    var logger = options.logger ||
                            (window && window.console) || {error: function () {}};
                    var version = options.version || "";
                    var versionTimestamp = options.versionTimestamp || "";

                    var stringifyError = function (error) {
                        var plainObject = {};
                        if (error && typeof error == "object") {
                            Object.getOwnPropertyNames(error).forEach(function (key) {
                                plainObject[key] = error[key];
                            });
                        }
                        return JSON.stringify(plainObject);
                    };

                    var logClientSideError = function (
                            message,
                            url,
                            line,
                            column,
                            error
                            ) {
                        // errors without line numbers, urls or columns aren't helpful, chuck them
                        if (!url || !column || !line)
                            return;

                        if (message.target && message.type) {
                            message = message.type;
                        }

                        if (error && error.stack) {
                            var findStackUrlRegExp = /\(([^)\s]+?):\d+:\d+\)/gm;
                            var findLastStackUrlRegExp =
                                    /\s*(https?:\/\/[^:\s]+?):\d+:\d+\s*$/gm;
                            var match;
                            var local = true;

                            // test for parens enclosed URLs in stack trace
                            while ((match = findStackUrlRegExp.exec(error.stack))) {
                                if (match && !options.scriptFilter.test(match[1])) {
                                    local = false;
                                    break;
                                }
                            }

                            // test for last URL in stack trace
                            if (local) {
                                while ((match = findLastStackUrlRegExp.exec(error.stack))) {
                                    if (match && !options.scriptFilter.test(match[1])) {
                                        local = false;
                                        break;
                                    }
                                }
                            }

                            // if stack trace shows us external scripts are buggy, don't log
                            if (!local)
                                return;
                        }

                        var errorStr = stringifyError(error);
                        var errorDescrip = {
                            message: message,
                            script: url,
                            line: line,
                            url: window && window.document ? window.document.URL : url,
                            column: column,
                            error: errorStr,
                            version: version,
                            versionTimestamp: versionTimestamp,
                            appName: window.appName || "unknown",
                        };

                        logger.error(errorStr);

                        var trackableUrl =
                                url && (!options.scriptFilter || options.scriptFilter.test(url));

                        if (trackableUrl) {
                            var isNewError = !errorEquals(errorDescrip, lastError);

                            // don't track the same error over and over again
                            if (isNewError) {
                                lastError = errorDescrip;
                                tracker(errorDescrip);
                            }
                        }
                    };

                    if (typeof window !== "undefined") {
                        window.onerror = logClientSideError;
                        if (window.errorTracker) {
                            delete window.errorTracker;
                        }
                    } else {
                        return logClientSideError;
                    }
                };
            });
        </script>
        <script>
            window._204 = [];
            window._400 = [];
            if (window.errorTracker) {
                window.errorTracker(
                        function (error) {
                            window._400.push({key: "page.error.javascript", value: error});
                        },
                        {
                            scriptFilter: new RegExp(
                                    "^/|^" + location.protocol + "//" + location.host
                                    ),
                            version: "81ebf73f374a0d4983c6a4ff2a1712efa8b6c43d",
                            versionTimestamp: "1653496058138",
                        }
                );
            }
        </script>
        <script>
            window.publicPathOverride = "" !== "" ? "" : null;
        </script>
        <link
            href="https://d3njjcbhbojbot.cloudfront.net/webapps/r2-builds/authentication/allStyles.e8e06e38e35e57385387.css"
            data-href="https://d3njjcbhbojbot.cloudfront.net/webapps/r2-builds/authentication/allStyles.e8e06e38e35e57385387.css"
            rel="stylesheet"
            />
        <title>
            Reset Password
        </title>
        <meta
            data-react-helmet="true"
            name="description"
            content="7,000+ courses from schools like Stanford and Yale - no application required. Build career skills in data science, computer science, business, and more."
            />
        <meta
            data-react-helmet="true"
            name="image"
            content="https://s3.amazonaws.com/coursera/media/Grid_Coursera_Partners_updated.png"
            />
        <meta
            data-react-helmet="true"
            property="og:title"
            content="Reset Password"
            />
        <meta
            data-react-helmet="true"
            property="og:description"
            content="7,000+ courses from schools like Stanford and Yale - no application required. Build career skills in data science, computer science, business, and more."
            />
        <meta
            data-react-helmet="true"
            property="og:url"
            content="https://www.coursera.org/reset/confirm/aE3JCWY2ipVia_eZDz7kFrvjwE0NCCi3WlhuNjO1THlxqyFshdX4vXHHrNU2vp4YFR-Tq6sTnK74TrplcfA4wg.r7HwbKqwigkV9Q-br8DfnQ.Q2Uzg0XUxjZQTfKrnhXxYzMOzup04US1th_j52CNrZta1k1pEekaUBem5Tmhbs9fv91i6frDgtC2Zhs9b4hJnZ4HkAqxnBR2eB9HxFIyNmAHYLr2sbV3NQrKKLCelkBk"
            />
        <meta
            data-react-helmet="true"
            property="og:image"
            content="https://s3.amazonaws.com/coursera/media/Grid_Coursera_Partners_updated.png"
            />
        <meta data-react-helmet="true" property="og:locale" content="en_US" />
        <meta data-react-helmet="true" property="og:type" content="website" />
        <link
            data-react-helmet="true"
            rel="canonical"
            href="https://www.coursera.org/reset/confirm/aE3JCWY2ipVia_eZDz7kFrvjwE0NCCi3WlhuNjO1THlxqyFshdX4vXHHrNU2vp4YFR-Tq6sTnK74TrplcfA4wg.r7HwbKqwigkV9Q-br8DfnQ.Q2Uzg0XUxjZQTfKrnhXxYzMOzup04US1th_j52CNrZta1k1pEekaUBem5Tmhbs9fv91i6frDgtC2Zhs9b4hJnZ4HkAqxnBR2eB9HxFIyNmAHYLr2sbV3NQrKKLCelkBk"
            />

        <style
            data-aphrodite="_1l1hu98 _1j095b7 _1qfi0x77 _e296pg keyframe_1mfzdnn _1hwtb43 _jyhj5r _1vbo4s _6zvbayr _pebu3zt _wwcfwz _8ekd2y _181t6pv _1nhfz2 _liimpa"
            >
            ._1l1hu98 {
                min-width: 360px;
                background-color: #fff;
                box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
                position: fixed;
                width: 100%;
                left: 0px;
                right: 0px;
                top: 0px;
                -webkit-transition: top 0.2s ease-in-out;
                -moz-transition: top 0.2s ease-in-out;
                transition: top 0.2s ease-in-out;
                z-index: 4000;
            }
            ._1j095b7 {
                width: 127px;
                height: 32px;
            }
            ._1qfi0x77 {
                -webkit-box-align: center;
                -ms-flex-align: center;
                -webkit-align-items: center;
                align-items: center;
                display: -webkit-box;
                display: -moz-box;
                display: -ms-flexbox;
                display: -webkit-flex;
                display: flex;
            }
            ._e296pg {
                position: relative;
            }
            @keyframes keyframe_1mfzdnn {
                0% {
                    -webkit-transform: scale(0.1);
                    -ms-transform: scale(0.1);
                    transform: scale(0.1);
                    opacity: 0;
                }
                40% {
                    opacity: 1;
                }
                100% {
                    -webkit-transform: scale(1);
                    -ms-transform: scale(1);
                    transform: scale(1);
                    opacity: 0;
                }
            }
            ._1hwtb43 {
                text-indent: -9999em;
                border-style: solid;
                margin: 1.125rem 0 0 1.125rem;
                opacity: 0;
                position: absolute;
                top: -1.125rem;
                left: -1.125rem;
                -webkit-animation-name: keyframe_1mfzdnn;
                animation-name: keyframe_1mfzdnn;
                -webkit-animation-duration: 1s;
                animation-duration: 1s;
                -webkit-animation-iteration-count: infinite;
                animation-iteration-count: infinite;
            }
            ._jyhj5r {
                -webkit-box-lines: multiple;
                display: -webkit-box;
                display: -moz-box;
                display: -ms-flexbox;
                display: -webkit-flex;
                display: flex;
                -webkit-flex-wrap: wrap;
                -ms-flex-wrap: wrap;
                flex-wrap: wrap;
                margin-left: -12px;
                margin-right: -12px;
            }
            ._jyhj5r:after {

                display: table;
                clear: both;
            }
            ._1vbo4s {
                position: relative;
                min-height: 1px;
                width: 100%;
                padding-left: 12px;
                padding-right: 12px;
            }
            ._1vbo4s:after {

                display: table;
                clear: both;
            }
            @media (min-width: 320px) {
                ._1vbo4s {
                    width: 100%;
                }
            }
            @media (min-width: 608px) {
                ._1vbo4s {
                    width: 50%;
                }
            }
            @media (min-width: 824px) {
                ._1vbo4s {
                    width: 25%;
                }
            }
            ._6zvbayr {
                position: relative;
                min-height: 1px;
                width: 100%;
                padding-left: 12px;
                padding-right: 12px;
            }
            ._6zvbayr:after {

                display: table;
                clear: both;
            }
            @media (min-width: 320px) {
                ._6zvbayr {
                    width: 50%;
                }
            }
            @media (min-width: 608px) {
                ._6zvbayr {
                    width: 25%;
                }
            }
            @media (min-width: 824px) {
                ._6zvbayr {
                    width: 25%;
                }
            }
            ._pebu3zt {
                position: relative;
                min-height: 1px;
                width: 100%;
                padding-left: 12px;
                padding-right: 12px;
            }
            ._pebu3zt:after {

                display: table;
                clear: both;
            }
            @media (min-width: 320px) {
                ._pebu3zt {
                    width: 100%;
                }
            }
            @media (min-width: 608px) {
                ._pebu3zt {
                    width: 100%;
                }
            }
            @media (min-width: 824px) {
                ._pebu3zt {
                    width: 25%;
                }
            }
            ._wwcfwz:focus {
                outline: auto 3px #2a73cc;
            }
            ._8ekd2y {
                position: relative;
                min-height: 1px;
                width: 100%;
                padding-left: 0px;
                padding-right: 0px;
            }
            ._8ekd2y:after {

                display: table;
                clear: both;
            }
            ._181t6pv {
                position: relative;
                width: 151px;
                padding-bottom: 79.47019867549669%;
                height: 0px;
            }
            @media (max-width: 824px) {
                ._181t6pv {
                    max-width: 95vw;
                }
            }
            @media (max-width: 608px) {
                ._181t6pv {
                    max-width: 90vw;
                }
            }
            ._1nhfz2 {
                position: absolute;
                top: 0px;
                left: 0px;
                width: 100%;
                height: 100%;
            }
            ._liimpa {
                position: relative;
                width: 28px;
                padding-bottom: 100%;
                height: 0px;
            }
            @media (max-width: 824px) {
                ._liimpa {
                    max-width: 95vw;
                }
            }
            @media (max-width: 608px) {
                ._liimpa {
                    max-width: 90vw;
                }
            }
            #logo_coreui{
                height: 100px;
            }
            #khung_tren{
                height: 100px;
            }
            #Headerlogo{
                width: 100%;
                margin-left: 450px;
            }
            #container_bt3{
                text-align: center;

            }
            #main{
                margin-top: 80px;
            }
        </style>

        <style data-emotion="css 1mapz78" data-s="">
            .css-1mapz78 {
                margin-top: 25px;
                max-width: 151px;
                max-height: 120px;
            }
            .css-1mapz78 img {
                display: block;
                margin: 0 auto;
            }
        </style>

    </head>
    <body>
        <div id="fb-root"></div>
        <div id="rendered-content">
            <div class="rc-MetatagsWrapper">
                <div class="rc-AuthenticationApp">
                    <div class="rc-ResetConfirmPage">
                        <span class="rc-PageHeaderWrapper"
                              ><div>
                                <header
                                    class="rc-DesktopHeaderControls"
                                    data-catchpoint="page-header-controls"
                                    >
                                    <div class="rc-MobilePromoOption"><span></span></div>
                                    <div class="smart-scroll-container">
                                        <div
                                            class="_1l1hu98"
                                            style="z-index: 3000; box-shadow: none; max-width: 100vw"
                                            >
                                            <div class="rc-PageHeader" data-e2e="page-header">
                                                <div id="khung_tren" class="bt3-navbar c-ph-nav full-width">
                                                    <div id="container_bt3" class="c-container bt3-container-fluid">
                                                        <div
                                                            class="align-items-vertical-center horizontal-box sr-only"
                                                            ></div>
                                                        <div 
                                                               
                                                            class="header-logo-wrapper"
                                                            style="float: left"
                                                            >
                                                            <div id="Headerlogo"
                                                                class="rc-HeaderLogo c-ph-logo bt3-navbar-header horizontal-box align-items-vertical-center align-items-absolute-center"
                                                                >
                                                                <div
                                                                    class="horizontal-box align-items-vertical-center"
                                                                    >
                                                                    <div class="m-a-0 body">
                                                                        <a
                                                                            data-click-key="authentication.reset_password_page.click.logo"
                                                                            data-click-value='{"href":"/","namespace":{"action":"click","app":"authentication","component":"logo","page":"reset_password_page"},"schema_type":"FRONTEND"}'
                                                                            data-track="true"
                                                                            data-track-app="authentication"
                                                                            data-track-page="reset_password_page"
                                                                            data-track-action="click"
                                                                            data-track-component="logo"
                                                                            data-track-href="/"
                                                                            href="login"
                                                                            to="/"
                                                                            class="c-logo horizontal-box align-items-vertical-center nostyle"
                                                                            aria-label="Coursera"
                                                                            ><div
                                                                                style="
                                                                                display: flex;
                                                                                justify-content: center;
                                                                                align-items: center;
                                                                                "
                                                                                >
                                                                                <img
                                                                                    id="logo_coreui"
                                                                                    src="https://avatars.githubusercontent.com/u/36859861?s=280&v=4"
                                                                                    class="rc-CourseraLogo _1j095b7"
                                                                                    alt="Coursera"
                                                                                    aria-hidden="true"
                                                                                    /></div
                                                                            ></a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div><div></div></div>
                                        </div>
                                    </div>
                                    <div class="height-placeholders" role="presentation">
                                        <div
                                            class="height-placeholder with-desktop-header-controls"
                                            ></div>
                                    </div>
                                </header>
                            </div>
                            <div>
                                <div
                                    data-track="true"
                                    data-track-app="authentication"
                                    data-track-page="reset_password_page"
                                    data-track-action="click"
                                    data-track-component="mobile_header_lite"
                                    class="rc-MobileHeader rc-MobileHeaderLite"
                                    role="presentation"
                                    >
                                    <div
                                        class="_1l1hu98"
                                        style="
                                        z-index: 3000;
                                        min-width: 200px;
                                        max-width: 100vw;
                                        box-shadow: none;
                                        "
                                        >
                                        <div
                                            class="c-mobile-header-controls horizontal-box isLohpRebrand align-items-spacebetween"
                                            >
                                            <span></span
                                            ><a
                                                data-click-key="authentication.reset_password_page.click.mobile_header_logo"
                                                data-click-value='{"href":"/","namespace":{"action":"click","app":"authentication","component":"mobile_header_logo","page":"reset_password_page"},"schema_type":"FRONTEND"}'
                                                data-track="true"
                                                data-track-app="authentication"
                                                data-track-page="reset_password_page"
                                                data-track-action="click"
                                                data-track-component="mobile_header_logo"
                                                data-track-href="/"
                                                href="/"
                                                to="/"
                                                class="c-mobile-logo horizontal-box align-items-vertical-center isLohpRebrand"
                                                ><div style="display: flex">
                                                    <img
                                                        src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE2LjIuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPgo8IURPQ1RZUEUgc3ZnIFBVQkxJQyAiLS8vVzNDLy9EVEQgU1ZHIDEuMS8vRU4iICJodHRwOi8vd3d3LnczLm9yZy9HcmFwaGljcy9TVkcvMS4xL0RURC9zdmcxMS5kdGQiPgo8c3ZnIHZpZXdCb3g9IjAgMCAxMTU1IDE2NCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIGNsaXAtcnVsZT0iZXZlbm9kZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgc3Ryb2tlLW1pdGVybGltaXQ9IjIiPjxwYXRoIGQ9Ik0xNTkuNzUgODEuNTRjMC00NC40OSAzNi42My04MC40NyA4Mi40My04MC40NyA0Ni4xMiAwIDgyLjc2IDM2IDgyLjc2IDgwLjQ3IDAgNDQuMTYtMzYuNjQgODAuOC04Mi43NiA4MC44LTQ1LjggMC04Mi40My0zNi42OC04Mi40My04MC44em0xMjUuNjEgMGMwLTIyLjI0LTE5LjMtNDEuODctNDMuMTgtNDEuODctMjMuNTUgMC00Mi44NSAxOS42My00Mi44NSA0MS44NyAwIDIyLjU3IDE5LjMgNDIuMiA0Mi44NSA0Mi4yIDIzLjkyIDAgNDMuMTgtMTkuNjMgNDMuMTgtNDIuMnptNzA1LjYzIDEuMzFjMC00OC43NCAzOS41OC04MS43OCA3NS41Ny04MS43OCAyNC41MyAwIDM4LjYgNy41MiA0OC4wOCAyMS45MmwzLjc3LTE5aDM2Ljc5djE1NS40aC0zNi43OWwtNC43NS0xNmMtMTAuNzkgMTEuNzgtMjQuMjEgMTktNDcuMSAxOS0zNS4zMy0uMDUtNzUuNTctMzEuMTMtNzUuNTctNzkuNTR6bTEyNS42MS0uMzNjLS4wOS0yMy41MjctMTkuNDctNDIuODM1LTQzLTQyLjgzNS0yMy41OSAwLTQzIDE5LjQxMS00MyA0M3YuMTY1YzAgMjEuNTkgMTkuMyA0MC44OSA0Mi44NiA0MC44OSAyMy44NSAwIDQzLjE0LTE5LjMgNDMuMTQtNDEuMjJ6TTk0NS43OCAyMlY0aC00MC4yM3YxNTUuMzloNDAuMjNWNzUuNjZjMC0yNS4xOSAxMi40NC0zOC4yNyAzNC0zOC4yNyAxLjQzIDAgMi43OS4xIDQuMTIuMjNMOTkxLjM2LjExYy0yMC45Ny4xMS0zNi4xNyA3LjMtNDUuNTggMjEuODl6bS00MDQuMjcuMDF2LTE4bC00MC4yMy4wOS4zNCAxNTUuMzcgNDAuMjMtLjA5LS4yMi04My43MmMtLjA2LTI1LjE4IDEyLjM1LTM4LjI5IDMzLjkzLTM4LjM0IDEuMzc2LjAwNCAyLjc1Mi4wODEgNC4xMi4yM0w1ODcuMSAwYy0yMSAuMTctMzYuMjIgNy4zOS00NS41OSAyMi4wMXpNMzM4Ljg4IDk5LjJWNC4wMWg0MC4yMlY5NC4zYzAgMTkuOTUgMTEuMTIgMzEuNzMgMzAuNDIgMzEuNzMgMjEuNTkgMCAzNC0xMy4wOSAzNC0zOC4yOFY0LjAxaDQwLjI0djE1NS4zOGgtNDAuMjF2LTE4Yy05LjQ4IDE0LjcyLTI0Ljg2IDIxLjkyLTQ2LjEyIDIxLjkyLTM1Ljk4LjAxLTU4LjU1LTI2LjE2LTU4LjU1LTY0LjExem0zOTEuNzQtMTcuNDhjLjA5LTQzLjUxIDMxLjIzLTgwLjc0IDgwLjYyLTgwLjY1IDQ1LjguMDkgNzguMTEgMzYuNzggNzggODAgLjAxIDQuMjczLS4zMyA4LjU0LTEgMTIuNzZsLTExOC40MS0uMjJjNC41NCAxOC42NSAxOS44OSAzMi4wOSA0My4xMiAzMi4xNCAxNC4wNiAwIDI5LjEyLTUuMTggMzguMy0xNi45NGwyNy40NCAyMmMtMTQuMTEgMTkuOTMtMzkgMzEuNjYtNjUuNDggMzEuNjEtNDYuNzUtLjE2LTgyLjY3LTM1LjIzLTgyLjU5LTgwLjd6bTExOC4xMi0xNi4xNGMtMi4yNi0xNS43LTE4LjU5LTI3Ljg0LTM3Ljg5LTI3Ljg3LTE4LjY1IDAtMzMuNzEgMTEuMDYtMzkuNjMgMjcuNzNsNzcuNTIuMTR6bS0yNjEuNCA1OS45NGwzNS43Ni0xOC43MmM1LjkxIDEyLjgxIDE3LjczIDIwLjM2IDM0LjQ4IDIwLjM2IDE1LjQzIDAgMjEuMzQtNC45MiAyMS4zNC0xMS44MiAwLTI1LTg0LjcxLTkuODUtODQuNzEtNjcgMC0zMS41MiAyNy41OC00OC4yNiA2MS43Mi00OC4yNiAyNS45NCAwIDQ4LjkyIDExLjQ5IDYxLjQgMzIuODNsLTM1LjQ0IDE4Ljc1Yy01LjI1LTEwLjUxLTE1LjEtMTYuNDItMjcuNTgtMTYuNDItMTIuMTQgMC0xOC4wNiA0LjI3LTE4LjA2IDExLjQ5IDAgMjQuMyA4NC43MSA4Ljg3IDg0LjcxIDY3IDAgMzAuMjEtMjQuNjIgNDguNTktNjQuMzUgNDguNTktMzMuODItLjAzLTU3LjQ2LTExLjE5LTY5LjI3LTM2Ljh6TTAgODEuNTRDMCAzNi43MyAzNi42My43NCA4Mi40My43NGMyNy45NDctLjE5NiA1NC4xODIgMTMuNzM3IDY5LjY3IDM3bC0zNC4zNCAxOS45MmE0Mi45NzIgNDIuOTcyIDAgMDAtMzUuMzMtMTguMzJjLTIzLjU1IDAtNDIuODUgMTkuNjMtNDIuODUgNDIuMiAwIDIyLjU3IDE5LjMgNDIuMiA0Mi44NSA0Mi4yYTQyLjUwMiA0Mi41MDIgMCAwMDM2LjMxLTIwbDM0IDIwLjI4Yy0xNS4zMDcgMjMuOTU1LTQxLjkwMiAzOC40MzEtNzAuMzMgMzguMjhDMzYuNjMgMTYyLjM0IDAgMTI1LjY2IDAgODEuNTR6IiBmaWxsPSIjMDA1NkQyIiBmaWxsLXJ1bGU9Im5vbnplcm8iLz48L3N2Zz4="
                                                        class="rc-CourseraLogo"
                                                        alt="Coursera"
                                                        aria-hidden="false"
                                                        /></div
                                                ></a>
                                            <div class="_1qfi0x77">
                                                <span class="iconContainer"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="rc-MobileSearchPage" style="z-index: 3001">
                                        <div class="mobile-search-body-wrapper"></div>
                                    </div>
                                </div></div
                            ></span>

                        <!-- ******************************************************************************************** -->
                        <form action="reset" method="POST">
                            <input type="text" name="check1" value="konull" class="form-control" id="floatingInput" placeholder="Username" hidden>
                            <input type="text" name="mail" value="${requestScope.email}" class="form-control" id="floatingInput" placeholder="Username" hidden>

                            <main id="main" role="main" class="auth-page-container">
                                <div class="auth-form-container">
                                    <h1 class="auth-page-title">Reset your password</h1>

                                    <p class="auth-page-message">
                                        <span
                                            >Changing password for:
                                            <b>${requestScope.email}</b></span
                                        >
                                    </p>
                                    <div class="auth-input-container">
                                        <div
                                            class="_1lrtjdg"
                                            aria-describedby="passwordInput_1-label"
                                            >
                                            <div>
                                                <label
                                                    class="_165itfg"
                                                    id="passwordInput_1-label"
                                                    for="passwordInput_1-input"
                                                    >Password</label
                                                ><span class="_oq4zya"
                                                       >(Between 8 and 72 characters)</span
                                                >
                                            </div>
                                            <div class="_kxlijz">
                                                <input
                                                    class="_6xfqva"
                                                    aria-label="Password"
                                                    aria-labelledby="passwordInput_1-label"
                                                    aria-invalid="false"
                                                    id="passwordInput_1-input"
                                                    name="newpassword1"
                                                    placeholder="New Password"
                                                    type="password"
                                                    required=""
                                                    pattern=".{8,72}"
                                                    data-e2e="password-field"
                                                    label="New Password"
                                                    componentid="newPassword"
                                                    title="Password must be between 8 and 72 characters"
                                                    value=""
                                                    />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="auth-input-container">
                                        <div
                                            class="_1lrtjdg"
                                            aria-describedby="newPasswordConfirm-label"
                                            >
                                            <div>
                                                <label
                                                    class="_165itfg"
                                                    id="newPasswordConfirm-label"
                                                    for="newPasswordConfirm-input"
                                                    >New Password Confirm</label
                                                >
                                            </div>
                                            <div class="_kxlijz">
                                                <input
                                                    class="_6xfqva"
                                                    aria-label="New Password Confirm"
                                                    aria-labelledby="newPasswordConfirm-label"
                                                    aria-invalid="false"
                                                    aria-required="true"
                                                    required=""
                                                    id="newPasswordConfirm-input"
                                                    name="newpassword2"
                                                    placeholder="New Password Confirm"
                                                    type="password"
                                                    value=""
                                                    />
                                            </div>
                                        </div>
                                    </div>
                                    <p class="thongbao1" style="color: red">${requestScope.notification1}</p>
                                    <button
                                        data-track="true"
                                        data-track-app="authentication"
                                        data-track-page="reset_password_page"
                                        data-track-action="click"
                                        data-track-component="password_reset_confirm_form_submit"
                                        class="primary auth-form-button"
                                        type="submit"
                                        >
                                        Change Password
                                    </button>

                                </div>
                            </main>
                        </form>
                        <!-- ******************************************************************************************** -->      

                    </div>
                </div>
                <script>
                    window.App = {
                        context: {
                            dispatcher: {
                                stores: {
                                    ApplicationStore: {
                                        csrfToken: "\u003CMISSING\u003E",
                                        mobilePromoEligible: false,
                                        requestCountryCode: "VN",
                                        userAgent: {
                                            source:
                                                    "Mozilla\u002F5.0 (Windows NT 10.0; Win64; x64) AppleWebKit\u002F537.36 (KHTML, like Gecko) Chrome\u002F102.0.5005.63 Safari\u002F537.36",
                                            browser: {name: "Chrome", version: "102.0.5005.63"},
                                            system: "unknown",
                                            platform: "Microsoft Windows",
                                            isMobileBrowser: false,
                                            isAndroid: false,
                                            isCourseraMobileApp: false,
                                            isMobile: false,
                                            isIOS: false,
                                            isMobilePhoneBrowser: false,
                                            isPrerender: false,
                                            isRobot: false,
                                            isOculusQuest: false,
                                        },
                                        userData: {id: null, authenticated: false},
                                        appName: "authentication",
                                    },
                                    NaptimeStore: {
                                        data: {},
                                        responseCache: {},
                                        elementsToUrlMapping: {},
                                        emptyQueries: [],
                                        errors: {},
                                    },
                                },
                            },
                            plugins: {},
                        },
                        plugins: {},
                    };
                    window.appName = "authentication";
                    window.__APOLLO_STATE__ = {};
                    window.renderedClassNames = [
                        "_1l1hu98",
                        "_1j095b7",
                        "_1qfi0x77",
                        "_e296pg",
                        "keyframe_1mfzdnn",
                        "_1hwtb43",
                        "_jyhj5r",
                        "_1vbo4s",
                        "_6zvbayr",
                        "_pebu3zt",
                        "_wwcfwz",
                        "_8ekd2y",
                        "_181t6pv",
                        "_1nhfz2",
                        "_liimpa",
                    ];
                    window.detectedTimezone = "Asia/Bangkok";
                    window.hitSSRDataCache = "false";
                </script>
                <script>
                    var loadScript = function (url, success, async) {
                        var newScript = document.createElement("script");
                        var scripts = document.getElementsByTagName("script");

                        newScript.type = "text/javascript";
                        newScript.async = async || false;
                        newScript.crossOrigin = "anonymous";

                        if (success) {
                            if (newScript.addEventListener) {
                                newScript.addEventListener(
                                        "load",
                                        function () {
                                            success();
                                        },
                                        false
                                        );
                            } else if (newScript.readyState) {
                                newScript.onreadystatechange = function () {
                                    if (this.readyState == "complete") {
                                        newScript.onreadystatechange = null;
                                        success();
                                    }
                                };
                            }
                        }

                        if (scripts && scripts.length) {
                            var lastScript = scripts[scripts.length - 1];
                            lastScript.parentNode.insertBefore(newScript, lastScript.nextSibling);
                        } else if (window.document && window.document.body) {
                            window.document.body.appendChild(newScript);
                        }

                        // IE9 will execute the proper order if src is set AFTER injection
                        newScript.src = url;
                    };

                    window.coursera = {};
                    window.appName = "authentication";
                    window.locale = "en";
                </script>
               
                <script>
                    if (!window.JSON) {
                        loadScript(
                                "https://d3njjcbhbojbot.cloudfront.net/web/js/vendor/json2.js"
                                );
                    }
                </script>
                </body>
                </html>
