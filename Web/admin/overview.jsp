<%@include file="../common.jsp"%> 
<html>
<head>
<style>
@import '../style.css';
</style>
</head>
<body>

<div style="line-height: 2em;margin-top:80px"><h1 style="text-align:right">Welcome to setting page</h1>
<p>
In particular, this primer describes the features of SOAP through various usage scenarios, and is intended to complement the normative text contained in SOAP Version 1.2 Part 1: Messaging Framework (hereafter [SOAP Part1]) and SOAP Version 1.2 Part 2: Adjuncts (hereafter [SOAP Part2]) of the SOAP Version 1.2 specifications.
</p>
<p>
It is expected that the reader has some familiarity with the basic syntax of XML, including the use of XML namespaces and infosets, and Web concepts such as URIs and HTTP. It is intended primarily for users of SOAP, such as application designers, rather than implementors of the SOAP specifications, although the latter may derive some benefit. This primer aims at highlighting the essential features of SOAP Version 1.2, not at completeness in describing every nuance or edge case. Therefore, there is no substitute for the main specifications to obtain a fuller understanding of SOAP. To that end, this primer provides extensive links to the main specifications wherever new concepts are introduced or used.</p>
<p>
[SOAP Part1] defines the SOAP envelope, which is a construct that defines an overall framework for representing the contents of a SOAP message, identifying who should deal with all or part of it, and whether handling such parts are optional or mandatory. It also defines a protocol binding framework, which describes how the specification for a binding of SOAP onto another underlying protocol may be written.</p>
<p>
[SOAP Part2] defines a data model for SOAP, a particular encoding scheme for data types which may be used for conveying remote procedure calls (RPC), as well as one concrete realization of the underlying protocol binding framework defined in [SOAP Part1]. This binding allows the exchange of SOAP messages either as payload of a HTTP POST request and response, or as a SOAP message in the response to a HTTP GET.</p>

</div>
</body>
</html>