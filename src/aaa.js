(function(e) {
	if (e.WebVideoCtrl) {
		return
	}
	var t = function() {
		var t = "100%";
		var s = "100%";
		var n = "";
		var o = "";
		var i = {
			szContainerID : "",
			szColorProperty : "",
			szOcxClassId : "clsid:FDF0038A-CF64-4634-81AB-80F0A7946D6C",
			szMimeTypes : "application/webvideo-plugin-kit",
			iWndowType : 1,
			iPlayMode : 2,
			bWndFull : true,
			bDebugMode : false,
			cbSelWnd : null,
			cbEvent : null
		};
		var a = null;
		var u = 0;
		var c = [];
		var l = [];
		var p = [];
		var d = null;
		var f = null;
		var P = null;
		var h = null;
		var I = this;
		var v = null;
		var m = 1;
		var C = 2;
		var S = 200;
		var y = 403;
		var g = 0;
		var x = 1;
		var T = 2;
		var z = 3;
		var D = 4;
		var A = 5;
		var b = 6;
		var M = 0;
		var q = 2;
		var L = 3;
		var R = 21;
		var G = 0;
		var X = 1;
		var H = "IPCamera";
		var w = "IPDome";
		var Z = "IPZoom";
		var k = "<?xml version='1.0' encoding='utf-8'?><FileVersion>"
				+ "<Platform name='win32'>"
				+ "<npWebVideoKitPlugin.dll>3,0,6,1</npWebVideoKitPlugin.dll>"
				+ "<WebVideoKitActiveX.ocx>3,0,6,1</WebVideoKitActiveX.ocx>"
				+ "<PlayCtrl.dll>7,3,0,81</PlayCtrl.dll>"
				+ "<StreamTransClient.dll>1,1,3,5</StreamTransClient.dll>"
				+ "<SystemTransform.dll>2,5,1,7</SystemTransform.dll>"
				+ "<NetStream.dll>1,0,5,59</NetStream.dll>" + "</Platform>"
				+ "</FileVersion>";
		e.GetSelectWndInfo = function(e) {
			var t = h.loadXML(e);
			u = parseInt(r.$XML(t).find("SelectWnd").eq(0).text(), 10);
			if (null === v) {
				N()
			}
			var s = [];
			s.push("<RealPlayInfo>");
			s.push("<SelectWnd>" + u + "</SelectWnd>");
			s.push("</RealPlayInfo>");
			if (i.cbSelWnd) {
				i.cbSelWnd(h.loadXML(s.join("")))
			}
		};
		e.ZoomInfoCallback = function(e) {
			var t = I.findWndIndexByIndex(u);
			if (t != -1) {
				var r = l[t];
				t = I.findDeviceIndexByIP(r.szIP);
				if (t != -1) {
					var s = c[t];
					s.oProtocolInc.set3DZoom(s, r, e, {
						success : function(e) {
						},
						error : function() {
						}
					})
				}
			}
		};
		e.PluginEventHandler = function(e, t, r) {
			W("插件事件：PluginEventHandler iEventType：%s iParam1: %s, iParam2: %s",
					e, t, r);
			if (M == e || q == e) {
				I.I_Stop(t)
			} else if (R == e) {
				I.I_StopRecord(t)
			} else if (L == e) {
				I.I_StopVoiceTalk()
			} else {
			}
			if (i.cbEvent) {
				i.cbEvent(e, t, r)
			}
		};
		e.GetHttpInfo = function(e, t, r) {
			W("http响应返回：http状态：%s, http数据：%s", e, t);
			nt.prototype.processCallback(e, t)
		};
		var W = function() {
			if (i.bDebugMode) {
				var e = _(arguments);
				d._alert(e)
			}
		};
		var _ = function() {
			var e = arguments[0];
			for (var t = 1; t < arguments.length; t++) {
				e = e.replace("%s", arguments[t])
			}
			return e
		};
		var E = function(e) {
			return typeof e === "undefined"
		};
		var B = function() {
			var e = "";
			if (!h.browser().msie) {
				var r = navigator.mimeTypes.length;
				for (var a = 0; a < r; a++) {
					if (navigator.mimeTypes[a].type.toLowerCase() == i.szMimeTypes) {
						e = "<embed align='center' type='" + i.szMimeTypes
								+ "' width='" + t + "' height='" + s
								+ "' name='" + o + "' wndtype='" + i.iWndowType
								+ "' playmode='" + i.iPlayMode + "' colors='"
								+ i.szColorProperty + "'>"
					}
				}
			} else {
				e = "<object classid='" + i.szOcxClassId
						+ "' codebase='' standby='Waiting...' " + "id='" + n
						+ "' width='" + t + "' height='" + s
						+ "' align='center' >"
						+ "<param name='wndtype' value='" + i.iWndowType + "'>"
						+ "<param name='playmode' value='" + i.iPlayMode + "'>"
						+ "<param name='colors' value='" + i.szColorProperty
						+ "'></object>"
			}
			return e
		};
		var N = function() {
			if (a !== null) {
				var e = a.HWP_GetLocalConfig();
				v = h.loadXML(e)
			}
		};
		var F = function(e) {
			I.I_GetDeviceInfo(e.szIP, {
				success : function(t) {
					e.szDeviceType = r.$XML(t).find("deviceType").eq(0).text()
				}
			});
			I.I_GetAnalogChannelInfo(e.szIP, {
				success : function(t) {
					e.iAnalogChannelNum = r.$XML(t).find("VideoInputChannel",
							true).length
				}
			});
			I.I_GetAudioInfo(e.szIP, {
				success : function(t) {
					var s = r.$XML(t).find("audioCompressionType", true);
					if (s.length > 0) {
						var n = r.$XML(s).eq(0).text(), o = 0;
						if ("G.711ulaw" == n) {
							o = 1
						} else if ("G.711alaw" == n) {
							o = 2
						} else if ("G.726" == n) {
							o = 3
						}
						e.iAudioType = o
					}
				}
			})
		};
		var U = function() {
			var e = i.bWndFull ? 1 : 0;
			a.HWP_SetCanFullScreen(e)
		};
		var V = function(e) {
			var t = -1, r = -1, s = -1, n = null;
			if (Y(e)) {
				n = O(e);
				t = n.iRtspPort;
				s = n.iDevicePort
			} else {
				var o = K(e);
				var i = false;
				for (var a = 0; a < o.length; a++) {
					if (o[a].ipv4 == e.szIP || o[a].ipv6 == e.szIP) {
						i = true;
						break
					}
				}
				if (i) {
					n = O(e)
				} else {
					n = j(e);
					if (-1 == n.iRtspPort && -1 == n.iDevicePort) {
						n = O(e)
					}
				}
				t = n.iRtspPort;
				r = n.iHttpPort;
				s = n.iDevicePort
			}
			return n
		};
		var O = function(e) {
			var t = -1, s = -1, n = -1;
			e.oProtocolInc.getPortInfo(e, {
				async : false,
				success : function(e) {
					var o = r.$XML(e).find("AdminAccessProtocol", true);
					t = 554;
					for (var i = 0, a = o.length; i < a; i++) {
						if (r.$XML(o).eq(i).find("protocol").eq(0).text()
								.toLowerCase() === "rtsp") {
							t = parseInt(r.$XML(o).eq(i).find("portNo").eq(0)
									.text(), 10)
						}
						if (r.$XML(o).eq(i).find("protocol").eq(0).text()
								.toLowerCase() === "http") {
							s = parseInt(r.$XML(o).eq(i).find("portNo").eq(0)
									.text(), 10)
						}
						if (r.$XML(o).eq(i).find("protocol").eq(0).text()
								.toLowerCase() === "dev_manage") {
							n = parseInt(r.$XML(o).eq(i).find("portNo").eq(0)
									.text(), 10)
						}
					}
				},
				error : function() {
					t = -1;
					s = -1;
					n = -1
				}
			});
			return {
				iRtspPort : t,
				iHttpPort : s,
				iDevicePort : n
			}
		};
		var j = function(e) {
			var t = -1, s = -1, n = -1;
			e.oProtocolInc.getUPnPPortStatus(e, {
				async : false,
				success : function(e) {
					var o = r.$XML(e).find("portStatus", true);
					for (var i = 0, a = o.length; i < a; i++) {
						if (r.$XML(o).eq(i).find("internalPort").eq(0).text()
								.toLowerCase() == "rtsp") {
							t = parseInt(r.$XML(o).eq(i).find("externalPort")
									.eq(0).text(), 10)
						}
						if (r.$XML(o).eq(i).find("internalPort").eq(0).text()
								.toLowerCase() == "http") {
							s = parseInt(r.$XML(o).eq(i).find("externalPort")
									.eq(0).text(), 10)
						}
						if (r.$XML(o).eq(i).find("internalPort").eq(0).text()
								.toLowerCase() == "admin") {
							n = parseInt(r.$XML(o).eq(i).find("externalPort")
									.eq(0).text(), 10)
						}
					}
				},
				error : function() {
					t = -1;
					s = -1;
					n = -1
				}
			});
			return {
				iRtspPort : t,
				iHttpPort : s,
				iDevicePort : n
			}
		};
		var K = function(e) {
			var t = [];
			e.oProtocolInc.getNetworkBond(e, {
				async : false,
				success : function(s) {
					if (r.$XML(s).find("enabled").eq(0).text() == "true") {
						t.push({
							ipv4 : r.$XML(s).find("ipAddress").eq(0).text(),
							ipv6 : r.$XML(s).find("ipv6Address").eq(0).text()
						})
					} else {
						e.oProtocolInc.getNetworkInterface(e, {
							async : false,
							success : function(e) {
								var s = r.$XML(e)
										.find("NetworkInterface", true);
								for (var n = 0, o = s.length; n < o; n++) {
									t.push({
										ipv4 : r.$XML(e).find("ipAddress")
												.eq(0).text(),
										ipv6 : r.$XML(e).find("ipv6Address")
												.eq(0).text()
									});
									break
								}
							},
							error : function() {
							}
						})
					}
				},
				error : function() {
					e.oProtocolInc.getNetworkInterface(e, {
						async : false,
						success : function(e) {
							var s = r.$XML(e).find("NetworkInterface", true);
							for (var n = 0, o = s.length; n < o; n++) {
								t.push({
									ipv4 : r.$XML(e).find("ipAddress").eq(0)
											.text(),
									ipv6 : r.$XML(e).find("ipv6Address").eq(0)
											.text()
								});
								break
							}
						},
						error : function() {
						}
					})
				}
			});
			return t
		};
		var Y = function(e) {
			var t = false;
			e.oProtocolInc.getPPPoEStatus(e, {
				async : false,
				success : function(e) {
					if (r.$XML(e).find("ipAddress", true).length > 0) {
						t = true
					} else if (r.$XML(e).find("ipv6Address", true).length > 0) {
						t = true
					} else {
						t = false
					}
				},
				error : function() {
					t = false
				}
			});
			return t
		};
		var J = function() {
			for (var e = 0, t = l.length; e < t; e++) {
				var r = l[e];
				if (r.bSound) {
					var s = a.HWP_CloseSound();
					if (0 == s) {
						r.bSound = false
					}
				}
			}
		};
		var Q = function(e) {
			if (e.oStreamCapa.bObtained) {
				return
			}
			if (e.oProtocolInc instanceof ot) {
				if (H == e.szDeviceType || w == e.szDeviceType
						|| Z == e.szDeviceType) {
					e.oProtocolInc
							.getStreamChannels(
									e,
									{
										async : false,
										success : function(t) {
											e.oStreamCapa.bObtained = true;
											var r = $(t).find(
													"streamingTransport", true).length;
											for (var s = 0; s < r; s++) {
												if ($(t).find(
														"streamingTransport")
														.eq(s).text()
														.toLowerCase() == "shttp") {
													e.oStreamCapa.bObtained = true;
													e.oStreamCapa.bSupportShttpPlay = true;
													e.oStreamCapa.bSupportShttpPlayback = true;
													e.oStreamCapa.bSupportShttpsPlay = true;
													e.oStreamCapa.bSupportShttpsPlayback = true;
													e.oStreamCapa.iIpChanBase = 1;
													break
												}
											}
										},
										error : function() {
										}
									})
				} else {
					e.oProtocolInc
							.getSDKCapa(
									e,
									{
										async : false,
										success : function(t) {
											e.oStreamCapa.bObtained = true;
											e.oStreamCapa.bSupportShttpPlay = r
													.$XML(t)
													.find("isSupportHttpPlay")
													.eq(0).text() === "true";
											e.oStreamCapa.bSupportShttpPlayback = r
													.$XML(t)
													.find(
															"isSupportHttpPlayback")
													.eq(0).text() === "true";
											e.oStreamCapa.bSupportShttpsPlay = r
													.$XML(t)
													.find("isSupportHttpsPlay")
													.eq(0).text() === "true";
											e.oStreamCapa.bSupportShttpsPlayback = r
													.$XML(t)
													.find(
															"isSupportHttpsPlayback")
													.eq(0).text() === "true";
											e.oStreamCapa.bSupportShttpPlaybackTransCode = r
													.$XML(t)
													.find(
															"isSupportHttpTransCodePlayback")
													.eq(0).text() === "true";
											e.oStreamCapa.bSupportShttpsPlaybackTransCode = r
													.$XML(t)
													.find(
															"isSupportHttpsTransCodePlayback")
													.eq(0).text() === "true";
											if (r.$XML(t).find("ipChanBase",
													true).length > 0) {
												e.oStreamCapa.iIpChanBase = parseInt(
														r.$XML(t).find(
																"ipChanBase")
																.eq(0).text(),
														10)
											}
										},
										error : function() {
											e.oStreamCapa.bObtained = true
										}
									})
				}
			}
		};
		var et = function(e) {
			var t = {
				TransFrameRate : "",
				TransResolution : "",
				TransBitrate : ""
			};
			h.extend(t, e);
			if (t.TransFrameRate == "" || t.TransResolution == ""
					|| t.TransBitrate == "") {
				return ""
			}
			var r = [];
			r.push("<?xml version='1.0' encoding='UTF-8'?>");
			r.push("<CompressionInfo>");
			r.push("<TransFrameRate>" + t.TransFrameRate + "</TransFrameRate>");
			r.push("<TransResolution>" + t.TransResolution
					+ "</TransResolution>");
			r.push("<TransBitrate>" + t.TransBitrate + "</TransBitrate>");
			r.push("</CompressionInfo>");
			return r.join("")
		};
		this.I_InitPlugin = function(e, r, n) {
			t = e;
			s = r;
			h.extend(i, n)
		};
		this.I_InsertOBJECTPlugin = function(t) {
			if (!E(t)) {
				i.szContainerID = t
			}
			if (document.getElementById(i.szContainerID) == null) {
				return -1
			}
			if (document.getElementById(n) != null
					|| document.getElementsByName(n).length != 0) {
				return -1
			}
			document.getElementById(i.szContainerID).innerHTML = B();
			if (!h.browser().msie) {
				a = document.getElementsByName(o)[0]
			} else {
				a = document.getElementById(n)
			}
			if (a == null && a.object == null) {
				return -1
			} else {
				if (typeof e.attachEvent == "object" && h.browser().msie) {
					a.attachEvent("GetSelectWndInfo", GetSelectWndInfo);
					a.attachEvent("ZoomInfoCallback", ZoomInfoCallback);
					a.attachEvent("GetHttpInfo", GetHttpInfo);
					a.attachEvent("PluginEventHandler", PluginEventHandler)
				} else {
				}
			}
			N();
			return 0
		};
		this.I_WriteOBJECT_XHTML = function() {
			document.writeln(B());
			if (!h.browser().msie) {
				a = document.getElementsByName(o)[0]
			} else {
				a = document.getElementById(n)
			}
			if (a == null && a.object == null) {
				return -1
			} else {
				if (h.browser().msie) {
					a.attachEvent("GetSelectWndInfo", GetSelectWndInfo);
					a.attachEvent("ZoomInfoCallback", ZoomInfoCallback);
					a.attachEvent("GetHttpInfo", GetHttpInfo);
					a.attachEvent("PluginEventHandler", PluginEventHandler)
				}
			}
			N();
			return 0
		};
		this.I_OpenFileDlg = function(e) {
			var t = a.HWP_OpenFileBrowser(e, "");
			if (t != null) {
				if (1 == e) {
					if (t.length > 100) {
						return -1
					}
				} else {
					if (t.length > 130) {
						return -1
					}
				}
			} else {
				return ""
			}
			return t
		};
		this.I_GetLocalCfg = function() {
			var e = a.HWP_GetLocalConfig(), t = [];
			v = h.loadXML(e);
			t.push("<LocalConfigInfo>");
			t.push("<ProtocolType>"
					+ r.$XML(v).find("ProtocolType").eq(0).text()
					+ "</ProtocolType>");
			t.push("<PackgeSize>" + r.$XML(v).find("PackgeSize").eq(0).text()
					+ "</PackgeSize>");
			t.push("<PlayWndType>" + r.$XML(v).find("PlayWndType").eq(0).text()
					+ "</PlayWndType>");
			t.push("<BuffNumberType>"
					+ r.$XML(v).find("BuffNumberType").eq(0).text()
					+ "</BuffNumberType>");
			t.push("<RecordPath>" + r.$XML(v).find("RecordPath").eq(0).text()
					+ "</RecordPath>");
			t.push("<CapturePath>" + r.$XML(v).find("CapturePath").eq(0).text()
					+ "</CapturePath>");
			t.push("<PlaybackFilePath>"
					+ r.$XML(v).find("PlaybackFilePath").eq(0).text()
					+ "</PlaybackFilePath>");
			t.push("<PlaybackPicPath>"
					+ r.$XML(v).find("PlaybackPicPath").eq(0).text()
					+ "</PlaybackPicPath>");
			t.push("<DownloadPath>"
					+ r.$XML(v).find("DownloadPath").eq(0).text()
					+ "</DownloadPath>");
			t.push("<IVSMode>" + r.$XML(v).find("IVSMode").eq(0).text()
					+ "</IVSMode>");
			t.push("<CaptureFileFormat>"
					+ r.$XML(v).find("CaptureFileFormat").eq(0).text()
					+ "</CaptureFileFormat>");
			t.push("</LocalConfigInfo>");
			return h.loadXML(t.join(""))
		};
		this.I_SetLocalCfg = function(e) {
			var t = h.loadXML(e), s = -1;
			r.$XML(v).find("ProtocolType").eq(0).text(
					r.$XML(t).find("ProtocolType").eq(0).text());
			r.$XML(v).find("PackgeSize").eq(0).text(
					r.$XML(t).find("PackgeSize").eq(0).text());
			r.$XML(v).find("PlayWndType").eq(0).text(
					r.$XML(t).find("PlayWndType").eq(0).text());
			r.$XML(v).find("BuffNumberType").eq(0).text(
					r.$XML(t).find("BuffNumberType").eq(0).text());
			r.$XML(v).find("RecordPath").eq(0).text(
					r.$XML(t).find("RecordPath").eq(0).text());
			r.$XML(v).find("CapturePath").eq(0).text(
					r.$XML(t).find("CapturePath").eq(0).text());
			r.$XML(v).find("PlaybackFilePath").eq(0).text(
					r.$XML(t).find("PlaybackFilePath").eq(0).text());
			r.$XML(v).find("PlaybackPicPath").eq(0).text(
					r.$XML(t).find("PlaybackPicPath").eq(0).text());
			r.$XML(v).find("DownloadPath").eq(0).text(
					r.$XML(t).find("DownloadPath").eq(0).text());
			r.$XML(v).find("IVSMode").eq(0).text(
					r.$XML(t).find("IVSMode").eq(0).text());
			r.$XML(v).find("CaptureFileFormat").eq(0).text(
					r.$XML(t).find("CaptureFileFormat").eq(0).text());
			s = a.HWP_SetLocalConfig(h.toXMLStr(v));
			return s ? 0 : -1
		};
		var tt = function(e, t, r, s, n, o, i) {
			var a = {
				protocol : t,
				success : null,
				error : null
			};
			h.extend(a, i);
			h.extend(a, {
				success : function(a) {
					var u = new rt;
					u.szIP = e;
					if (t == 2) {
						u.szHttpProtocol = "https://";
						u.iHttpsPort = r
					} else {
						u.szHttpProtocol = "http://";
						u.iHttpPort = r
					}
					u.iCGIPort = r;
					u.szAuth = s;
					u.iDeviceProtocol = n;
					u.oProtocolInc = o;
					c.push(u);
					W("使用%s协议登录成功", n);
					F(u);
					U();
					if (i.success) {
						i.success(a)
					}
				},
				error : function(e, t) {
					if (i.error) {
						i.error(e, t)
					}
				}
			});
			o.login(e, r, s, a)
		};
		this.I_Login = function(e, t, r, s, n, o) {
			var i = this.findDeviceIndexByIP(e);
			if (i != -1) {
				W("设备已经登录过");
				return -1
			}
			var a = f;
			var u = m;
			if (!E(o.cgi)) {
				if (m == o.cgi) {
					a = f;
					u = m
				} else {
					a = P;
					u = C
				}
			}
			var c = "";
			if (m == u) {
				c = h.Base64.encode(":" + s + ":" + n);
				var l = {
					success : null,
					error : null
				};
				h.extend(l, o);
				h.extend(l, {
					error : function(i, l) {
						c = h.Base64.encode(s + ":" + n);
						u = m;
						a = f;
						var p = {
							success : null,
							error : null
						};
						h.extend(p, o);
						h.extend(p, {
							error : function() {
								if (!E(o.cgi)) {
									if (o.error) {
										o.error(i, l)
									}
									return
								}
								c = h.Base64.encode(":" + s + ":" + n);
								u = C;
								a = P;
								var p = {
									success : null,
									error : null
								};
								h.extend(p, o);
								h.extend(p, {
									error : function(i, l) {
										c = h.Base64.encode(s + ":" + n);
										u = C;
										a = P;
										var p = {
											success : null,
											error : null
										};
										h.extend(p, o);
										h.extend(p, {
											error : function() {
												if (o.error) {
													o.error(i, l)
												}
											}
										});
										tt(e, t, r, c, u, a, p)
									}
								});
								tt(e, t, r, c, u, a, p)
							}
						});
						tt(e, t, r, c, u, a, p)
					}
				});
				tt(e, t, r, c, u, a, l)
			} else {
				c = h.Base64.encode(":" + s + ":" + n);
				u = C;
				a = P;
				var l = {
					success : null,
					error : null
				};
				h.extend(l, o);
				h.extend(l, {
					error : function(i, l) {
						c = h.Base64.encode(s + ":" + n);
						u = C;
						a = P;
						var p = {
							success : null,
							error : null
						};
						h.extend(p, o);
						h.extend(p, {
							error : function() {
								if (o.error) {
									o.error(i, l)
								}
							}
						});
						tt(e, t, r, c, u, a, p)
					}
				});
				tt(e, t, r, c, u, a, l)
			}
		};
		this.I_Logout = function(e) {
			var t = this.findDeviceIndexByIP(e);
			if (t != -1) {
				c.splice(t, 1);
				return 0
			}
			return -1
		};
		this.I_GetAudioInfo = function(e, t) {
			var r = this.findDeviceIndexByIP(e);
			if (r != -1) {
				var s = c[r];
				var n = {
					success : null,
					error : null
				};
				h.extend(n, t);
				s.oProtocolInc.getAudioInfo(s, n)
			}
		};
		this.I_GetDeviceInfo = function(e, t) {
			var r = this.findDeviceIndexByIP(e);
			if (r != -1) {
				var s = c[r];
				var n = {
					success : null,
					error : null
				};
				h.extend(n, t);
				s.oProtocolInc.getDeviceInfo(s, n)
			}
		};
		this.I_GetAnalogChannelInfo = function(e, t) {
			var r = this.findDeviceIndexByIP(e);
			if (r != -1) {
				var s = c[r];
				var n = {
					success : null,
					error : null
				};
				h.extend(n, t);
				s.oProtocolInc.getAnalogChannelInfo(s, n)
			}
		};
		this.I_GetDigitalChannelInfo = function(e, t) {
			var r = this.findDeviceIndexByIP(e);
			if (r != -1) {
				var s = c[r];
				var n = {
					success : null,
					error : null
				};
				h.extend(n, t);
				s.oProtocolInc.getDigitalChannelInfo(s, n)
			}
		};
		this.I_GetZeroChannelInfo = function(e, t) {
			var r = this.findDeviceIndexByIP(e);
			if (r != -1) {
				var s = c[r];
				var n = {
					success : null,
					error : null
				};
				h.extend(n, t);
				s.oProtocolInc.getZeroChannelInfo(s, n)
			}
		};
		this.I_StartRealPlay = function(e, t) {
			var s = this.findDeviceIndexByIP(e), n = -1, o = "", i = "", a = -1, p = 0, d = 0, f = false;
			var P = {
				iWndIndex : u,
				iStreamType : 1,
				iChannelID : 1,
				bZeroChannel : false
			};
			h.extend(P, t);
			if (s != -1) {
				Q(c[s]);
				var I = c[s];
				var m = parseInt(r.$XML(v).find("ProtocolType").eq(0).text(),
						10);
				if (m == G && I.oStreamCapa.bSupportShttpPlay) {
					W("SHTTP RealPlay");
					o = I.oProtocolInc.CGI.startShttpRealPlay;
					i = "http://";
					d = P.iStreamType - 1;
					if (P.iChannelID <= I.iAnalogChannelNum) {
						p = P.iChannelID
					} else {
						p = I.oStreamCapa.iIpChanBase
								+ parseInt(P.iChannelID, 10)
								- I.iAnalogChannelNum - 1
					}
					f = true;
					if (!E(P.iPort)) {
						I.iHttpPort = P.iPort;
						a = P.iPort
					} else {
						if (I.szHttpProtocol == "https://") {
							if (-1 == I.iHttpPort) {
								I.iHttpPort = V(I).iHttpPort
							}
							a = I.iHttpPort
						} else {
							a = I.iCGIPort
						}
					}
				} else {
					W("RTSP RealPlay");
					o = I.oProtocolInc.CGI.startRealPlay;
					i = "rtsp://";
					d = P.iStreamType;
					p = P.iChannelID;
					if (!E(P.iPort)) {
						I.iRtspPort = P.iPort
					}
					if (-1 == I.iRtspPort) {
						I.iRtspPort = V(I).iRtspPort
					}
					a = I.iRtspPort
				}
				if (-1 == a) {
					W("获取端口号失败");
					return n
				}
				h.extend(P, {
					urlProtocol : i,
					cgi : o,
					iPort : a,
					iStreamType : d,
					iChannelID : p
				});
				s = this.findWndIndexByIndex(P.iWndIndex);
				if (-1 == s) {
					n = I.oProtocolInc.startRealPlay(I, P)
				}
				if (-1 == n) {
					I.iRtspPort = -1
				} else {
					s = this.findWndIndexByIndex(P.iWndIndex);
					var C = l[s];
					C.bShttpIPChannel = f
				}
			}
			return n
		};
		this.I_Stop = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t];
				if (s.bRecord) {
					a.HWP_StopSave(s.iIndex)
				}
				if (s.bSound) {
					a.HWP_CloseSound()
				}
				if (s.bEZoom) {
					a.HWP_DisableZoom(s.iIndex)
				}
				r = a.HWP_Stop(e);
				if (0 == r) {
					l.splice(t, 1)
				}
			}
			return r
		};
		this.I_OpenSound = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t];
				if (!s.bSound) {
					r = a.HWP_OpenSound(e);
					if (0 == r) {
						s.bSound = true
					}
				}
			}
			return r
		};
		this.I_CloseSound = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t];
				if (s.bSound) {
					r = a.HWP_CloseSound();
					if (0 == r) {
						s.bSound = false
					}
				}
			}
			return r
		};
		this.I_SetVolume = function(e, t) {
			var r = -1;
			if (isNaN(parseInt(e, 10))) {
				return r
			}
			t = E(t) ? u : t;
			var s = this.findWndIndexByIndex(t);
			if (s != -1) {
				r = a.HWP_SetVolume(t, e)
			}
			return r
		};
		this.I_CapturePic = function(e, t) {
			t = E(t) ? u : t;
			var r = this.findWndIndexByIndex(t), s = -1;
			if (r != -1) {
				s = a.HWP_CapturePicture(t, e)
			}
			return s
		};
		this.I_StartRecord = function(e, t) {
			t = E(t) ? u : t;
			var r = this.findWndIndexByIndex(t), s = -1;
			if (r != -1) {
				var n = l[r];
				if (!n.bRecord) {
					s = a.HWP_StartSave(t, e);
					if (0 == s) {
						n.bRecord = true
					}
				}
			}
			return s
		};
		this.I_StopRecord = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t];
				if (s.bRecord) {
					r = a.HWP_StopSave(e);
					if (0 == r) {
						s.bRecord = false
					}
				}
			}
			return r
		};
		this.I_StartVoiceTalk = function(e, t) {
			if (isNaN(parseInt(t, 10))) {
				return -1
			}
			var r = this.findDeviceIndexByIP(e), s = -1;
			if (r != -1) {
				var n = c[r];
				if (!n.bVoiceTalk) {
					s = n.oProtocolInc.startVoiceTalk(n, t);
					if (0 == s) {
						c[r].bVoiceTalk = true
					}
				}
			}
			return s
		};
		this.I_StopVoiceTalk = function() {
			var e = a.HWP_StopVoiceTalk();
			for (var t = 0, r = c.length; t < r; t++) {
				if (c[t].bVoiceTalk) {
					c[t].bVoiceTalk = false;
					break
				}
			}
			return e
		};
		this.I_PTZControl = function(e, t, r) {
			var s = {
				iWndIndex : u,
				iPTZIndex : e,
				iPTZSpeed : 4
			};
			h.extend(s, r);
			h.extend(s, {
				async : false
			});
			var n = this.findWndIndexByIndex(s.iWndIndex);
			if (n != -1) {
				var o = l[n];
				n = this.findDeviceIndexByIP(o.szIP);
				if (n != -1) {
					var i = c[n];
					if (9 == e) {
						i.oProtocolInc.ptzAutoControl(i, t, o, s)
					} else {
						i.oProtocolInc.ptzControl(i, t, o, s)
					}
				}
			}
		};
		this.I_EnableEZoom = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t];
				if (!s.bEZoom) {
					r = a.HWP_EnableZoom(e, 0);
					if (0 == r) {
						s.bEZoom = true
					}
				}
			}
			return r
		};
		this.I_DisableEZoom = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t];
				if (s.bEZoom) {
					a.HWP_DisableZoom(e);
					s.bEZoom = false;
					return 0
				}
			}
			return r
		};
		this.I_Enable3DZoom = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t];
				if (!s.b3DZoom) {
					r = a.HWP_EnableZoom(e, 1);
					if (0 == r) {
						s.b3DZoom = true
					}
				}
			}
			return r
		};
		this.I_Disable3DZoom = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t];
				if (s.b3DZoom) {
					a.HWP_DisableZoom(e);
					s.b3DZoom = false;
					return 0
				}
			}
			return r
		};
		this.I_FullScreen = function(e) {
			a.HWP_FullScreenDisplay(e)
		};
		this.I_SetPreset = function(e, t) {
			var r = {
				iWndIndex : u,
				iPresetID : e
			};
			h.extend(r, t);
			var s = this.findWndIndexByIndex(r.iWndIndex);
			if (s != -1) {
				var n = l[s];
				s = this.findDeviceIndexByIP(n.szIP);
				if (s != -1) {
					var o = c[s];
					o.oProtocolInc.setPreset(o, n, r)
				}
			}
		};
		this.I_GoPreset = function(e, t) {
			var r = {
				iWndIndex : u,
				iPresetID : e
			};
			h.extend(r, t);
			var s = this.findWndIndexByIndex(r.iWndIndex);
			if (s != -1) {
				var n = l[s];
				s = this.findDeviceIndexByIP(n.szIP);
				if (s != -1) {
					var o = c[s];
					o.oProtocolInc.goPreset(o, n, r)
				}
			}
		};
		this.I_RecordSearch = function(e, t, r, s, n) {
			var o = this.findDeviceIndexByIP(e);
			if (o != -1) {
				var i = c[o];
				var a = {
					iChannelID : t,
					szStartTime : r,
					szEndTime : s,
					iSearchPos : 0,
					success : null,
					error : null
				};
				h.extend(a, n);
				i.oProtocolInc.recordSearch(i, a)
			}
		};
		this.I_StartPlayback = function(e, t) {
			var s = this.findDeviceIndexByIP(e), n = -1, o = "", i = "", a = -1, l = 1;
			var p = h.dateFormat(new Date, "yyyy-MM-dd");
			var d = {
				iWndIndex : u,
				iChannelID : 1,
				szStartTime : p + " 00:00:00",
				szEndTime : p + " 23:59:59"
			};
			h.extend(d, t);
			if (s != -1) {
				Q(c[s]);
				var f = c[s];
				var P = parseInt(r.$XML(v).find("ProtocolType").eq(0).text(),
						10);
				if (P == G && f.oStreamCapa.bSupportShttpPlay) {
					if (!E(d.oTransCodeParam)) {
						o = f.oProtocolInc.CGI.startTransCodePlayback
					} else {
						o = f.oProtocolInc.CGI.startShttpPlayback
					}
					i = "http://";
					if (d.iChannelID <= f.iAnalogChannelNum) {
						l = d.iChannelID
					} else {
						l = f.oStreamCapa.iIpChanBase
								+ parseInt(d.iChannelID, 10)
								- f.iAnalogChannelNum - 1
					}
					if (!E(d.iPort)) {
						f.iHttpPort = d.iPort;
						a = d.iPort
					} else {
						if (f.szHttpProtocol == "https://") {
							if (-1 == f.iHttpPort) {
								f.iHttpPort = V(f).iHttpPort
							}
							a = f.iHttpPort
						} else {
							a = f.iCGIPort
						}
					}
				} else {
					o = f.oProtocolInc.CGI.startPlayback;
					i = "rtsp://";
					l = d.iChannelID * 100 + 1;
					if (!E(d.iPort)) {
						f.iRtspPort = d.iPort
					}
					if (-1 == f.iRtspPort) {
						f.iRtspPort = V(f).iRtspPort
					}
					a = f.iRtspPort
				}
				if (-1 == a) {
					W("获取端口号失败");
					return n
				}
				h.extend(d, {
					urlProtocol : i,
					cgi : o,
					iPort : a,
					iChannelID : l
				});
				s = this.findWndIndexByIndex(d.iWndIndex);
				if (-1 == s) {
					d.szStartTime = d.szStartTime.replace(/[-:]/g, "").replace(
							" ", "T")
							+ "Z";
					d.szEndTime = d.szEndTime.replace(/[-:]/g, "").replace(" ",
							"T")
							+ "Z";
					n = f.oProtocolInc.startPlayback(f, d)
				}
				if (-1 == n) {
					f.iRtspPort = -1
				}
			}
			return n
		};
		this.I_ReversePlayback = function(e, t) {
			var s = this.findDeviceIndexByIP(e), n = -1, o = "", i = "", a = -1, l = -1;
			var p = h.dateFormat(new Date, "yyyy-MM-dd");
			var d = {
				iWndIndex : u,
				iChannelID : 1,
				bZeroChannel : false,
				szStartTime : p + " 00:00:00",
				szEndTime : p + " 23:59:59"
			};
			h.extend(d, t);
			if (s != -1) {
				Q(c[s]);
				var f = c[s];
				var P = parseInt(r.$XML(v).find("ProtocolType").eq(0).text(),
						10);
				if (P == G && f.oStreamCapa.bSupportShttpPlay) {
					o = f.oProtocolInc.CGI.startShttpReversePlayback;
					i = "http://";
					if (d.iChannelID <= f.iAnalogChannelNum) {
						l = d.iChannelID
					} else {
						l = f.oStreamCapa.iIpChanBase
								+ parseInt(d.iChannelID, 10)
								- f.iAnalogChannelNum - 1
					}
					if (!E(d.iPort)) {
						f.iHttpPort = d.iPort;
						a = d.iPort
					} else {
						if (f.szHttpProtocol == "https://") {
							if (-1 == f.iHttpPort) {
								f.iHttpPort = V(f).iHttpPort
							}
							a = f.iHttpPort
						} else {
							a = f.iCGIPort
						}
					}
				} else {
					o = f.oProtocolInc.CGI.startPlayback;
					i = "rtsp://";
					l = d.iChannelID * 100 + 1;
					if (!E(d.iPort)) {
						f.iRtspPort = d.iPort
					}
					if (-1 == f.iRtspPort) {
						f.iRtspPort = V(f).iRtspPort
					}
					a = f.iRtspPort
				}
				if (-1 == a) {
					W("获取端口号失败");
					return n
				}
				h.extend(d, {
					urlProtocol : i,
					cgi : o,
					iPort : a,
					iChannelID : l
				});
				s = this.findWndIndexByIndex(d.iWndIndex);
				if (-1 == s) {
					d.szStartTime = d.szStartTime.replace(/[-:]/g, "").replace(
							" ", "T")
							+ "Z";
					d.szEndTime = d.szEndTime.replace(/[-:]/g, "").replace(" ",
							"T")
							+ "Z";
					n = f.oProtocolInc.reversePlayback(f, d)
				}
			}
			if (-1 == n) {
				f.iRtspPort = -1
			}
			return n
		};
		this.I_Frame = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t], n = s.iPlayStatus;
				if (n == T || n == D) {
					r = a.HWP_FrameForward(e);
					if (0 == r) {
						s.iPlayStatus = D
					}
				}
			}
			return r
		};
		this.I_Pause = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t], n = s.iPlayStatus;
				if (n == T) {
					r = a.HWP_Pause(e);
					if (0 == r) {
						s.iPlayStatus = z
					}
				} else if (n == A) {
					r = a.HWP_Pause(e);
					if (0 == r) {
						s.iPlayStatus = b
					}
				}
			}
			return r
		};
		this.I_Resume = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t], n = s.iPlayStatus;
				if (n == z || n == D) {
					r = a.HWP_Resume(e);
					if (0 == r) {
						s.iPlayStatus = T
					}
				} else if (n == b) {
					r = a.HWP_Resume(e);
					if (0 == r) {
						s.iPlayStatus = A
					}
				}
			}
			return r
		};
		this.I_PlaySlow = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t];
				if (s.iPlayStatus == T) {
					r = a.HWP_Slow(e)
				}
			}
			return r
		};
		this.I_PlayFast = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = l[t];
				if (s.iPlayStatus == T) {
					r = a.HWP_Fast(e)
				}
			}
			return r
		};
		this.I_GetOSDTime = function(e) {
			e = E(e) ? u : e;
			var t = this.findWndIndexByIndex(e), r = -1;
			if (t != -1) {
				var s = a.HWP_GetOSDTime(e);
				return h.dateFormat(new Date(s * 1e3), "yyyy-MM-dd hh:mm:ss")
			}
			return r
		};
		this.I_StartDownloadRecord = function(e, t, r) {
			var s = this.findDeviceIndexByIP(e), n = -1;
			if (s != -1) {
				var o = c[s];
				var i = {
					szPlaybackURI : t,
					szFileName : r
				};
				n = o.oProtocolInc.startDownloadRecord(o, i)
			}
			return n
		};
		this.I_GetDownloadStatus = function(e) {
			var t = a.HWP_GetDownloadStatus(e);
			if (1 == t) {
				t = -1
			}
			return t
		};
		this.I_GetDownloadProgress = function(e) {
			return a.HWP_GetDownloadProgress(e)
		};
		this.I_StopDownloadRecord = function(e) {
			return a.HWP_StopDownload(e)
		};
		this.I_ExportDeviceConfig = function(e) {
			var t = this.findDeviceIndexByIP(e);
			var r = -1;
			if (t != -1) {
				var s = c[t];
				r = s.oProtocolInc.exportDeviceConfig(s)
			}
			return r
		};
		this.I_ImportDeviceConfig = function(e, t) {
			var r = this.findDeviceIndexByIP(e), s = -1;
			if (r != -1) {
				var n = c[r];
				var o = {
					szFileName : t
				};
				s = n.oProtocolInc.importDeviceConfig(n, o)
			}
			return s
		};
		this.I_RestoreDefault = function(e, t, r) {
			var s = {
				success : null,
				error : null
			};
			h.extend(s, r);
			var n = this.findDeviceIndexByIP(e);
			if (n != -1) {
				var o = c[n];
				o.oProtocolInc.restore(o, t, s)
			}
		};
		this.I_Restart = function(e, t) {
			var r = this.findDeviceIndexByIP(e);
			var s = {
				success : null,
				error : null
			};
			h.extend(s, t);
			if (r != -1) {
				var n = c[r];
				n.oProtocolInc.restart(n, s)
			}
		};
		this.I_Reconnect = function(e, t) {
			var r = this.findDeviceIndexByIP(e);
			var s = {
				success : null,
				error : null
			};
			h.extend(s, t);
			if (r != -1) {
				var n = c[r];
				n.oProtocolInc.login(n.szIP, n.iCGIPort, n.szAuth, s)
			}
		};
		this.I_StartUpgrade = function(e, t) {
			var r = this.findDeviceIndexByIP(e), s = -1;
			if (r != -1) {
				var n = c[r];
				var o = {
					szFileName : t
				};
				s = n.oProtocolInc.startUpgrade(n, o)
			}
			return s
		};
		this.I_UpgradeStatus = function() {
			return a.HWP_UpgradeStatus()
		};
		this.I_UpgradeProgress = function() {
			return a.HWP_UpgradeProgress()
		};
		this.I_StopUpgrade = function() {
			return a.HWP_StopUpgrade()
		};
		this.I_CheckPluginInstall = function() {
			var e = -1;
			var t = h.browser();
			if (t.chrome && parseInt(t.version, 10) > 45) {
				return -2
			}
			if (t.msie) {
				try {
					alert(11111);
					var r = new ActiveXObject(
							"WebVideoKitActiveX.WebVideoKitActiveXCtrl.1");
					e = 0;
					alert("e：" + e);
				} catch (s) {
				}
			} else {
				for (var n = 0, o = navigator.mimeTypes.length; n < o; n++) {
					if (navigator.mimeTypes[n].type.toLowerCase() == "application/webvideo-plugin-kit") {
						e = 0;
						alert(22222222);
						break
					}
				}
			}
			alert("e：" + e);
			return e
		};
		this.I_CheckPluginVersion = function() {
			if (!a.HWP_CheckPluginUpdate(k)) {
				return 0
			} else {
				return -1
			}
		};
		this.I_SendHTTPRequest = function(e, t, r) {
			var s = this.findDeviceIndexByIP(e);
			if (s < 0) {
				return
			}
			var n = c[s];
			var o = new nt;
			var i = n.szHttpProtocol + n.szIP + ":" + n.iCGIPort + "/" + t;
			var a = {
				type : "GET",
				url : i,
				auth : n.szAuth,
				success : null,
				error : null
			};
			h.extend(a, r);
			h.extend(a, {
				success : function(e) {
					if (r.success) {
						r.success(e)
					}
				},
				error : function(e, t) {
					if (r.error) {
						r.error(e, t)
					}
				}
			});
			o.setRequestParam(a);
			o.submitRequest()
		};
		this.I_RemoteConfig = function(e, t) {
			var r = this.findDeviceIndexByIP(e), s = -1, n = -1;
			var o = {
				iLan : 0,
				iDevicePort : -1,
				iType : 0
			};
			h.extend(o, t);
			if (r != -1) {
				var i = c[r];
				if (-1 == o.iDevicePort) {
					if (-1 == i.iDevicePort) {
						i.iDevicePort = V(i).iDevicePort;
						n = i.iDevicePort;
						if (-1 == n) {
							return s
						}
					} else {
						n = i.iDevicePort
					}
				} else {
					n = o.iDevicePort
				}
				if (":" == h.Base64.decode(i.szAuth)[0]) {
					var u = h.Base64.decode(i.szAuth).split(":")[1];
					var l = h.Base64.decode(i.szAuth).split(":")[2]
				} else {
					var u = h.Base64.decode(i.szAuth).split(":")[0];
					var l = h.Base64.decode(i.szAuth).split(":")[1]
				}
				var p = "<RemoteInfo><DeviceInfo><DeviceType>" + o.iType
						+ "</DeviceType>" + "<LanType>" + o.iLan + "</LanType>"
						+ "<IP>" + e + "</IP>" + "<Port>" + n + "</Port>"
						+ "<UName>" + u + "</UName>" + "<PWD>"
						+ h.Base64.encode(l)
						+ "</PWD></DeviceInfo></RemoteInfo>";
				return a.HWP_ShowRemConfig(p)
			}
			return s
		};
		this.I_ChangeWndNum = function(e) {
			if (isNaN(parseInt(e, 10))) {
				return -1
			}
			a.HWP_ArrangeWindow(e);
			return 0
		};
		this.I_GetLastError = function() {
			return a.HWP_GetLastError()
		};
		this.I_GetWindowStatus = function(e) {
			if (E(e)) {
				var t = [];
				h.extend(t, l);
				return t
			} else {
				var r = this.findWndIndexByIndex(e);
				if (r != -1) {
					var t = {};
					h.extend(t, l[r]);
					return t
				} else {
					return null
				}
			}
		};
		this.I_GetIPInfoByMode = function(e, t, r, s) {
			return a.HWP_GetIpInfoByMode(e, t, r, s)
		};
		this.findDeviceIndexByIP = function(e) {
			for (var t = 0; t < c.length; t++) {
				if (c[t].szIP == e) {
					return t
				}
			}
			return -1
		};
		this.findWndIndexByIndex = function(e) {
			for (var t = 0; t < l.length; t++) {
				if (l[t].iIndex == e) {
					return t
				}
			}
			return -1
		};
		var rt = function() {
			this.szIP = "";
			this.szHostName = "";
			this.szAuth = "";
			this.szHttpProtocol = "http://";
			this.iCGIPort = 80;
			this.iDevicePort = -1;
			this.iHttpPort = -1;
			this.iHttpsPort = -1;
			this.iRtspPort = -1;
			this.iAudioType = 1;
			this.iDeviceProtocol = m;
			this.oProtocolInc = null;
			this.iAnalogChannelNum = 0;
			this.szDeviceType = "";
			this.bVoiceTalk = false;
			this.oStreamCapa = {
				bObtained : false,
				bSupportShttpPlay : false,
				bSupportShttpPlayback : false,
				bSupportShttpsPlay : false,
				bSupportShttpsPlayback : false,
				bSupportShttpPlaybackTransCode : false,
				bSupportShttpsPlaybackTransCode : false,
				iIpChanBase : 1
			}
		};
		var st = function() {
			this.iIndex = 0;
			this.szIP = "";
			this.iChannelID = "";
			this.iPlayStatus = g;
			this.bSound = false;
			this.bRecord = false;
			this.bPTZAuto = false;
			this.bEZoom = false;
			this.b3DZoom = false
		};
		var nt = function() {
			this.options = {
				type : "GET",
				url : "",
				auth : "",
				timeout : 1e4,
				data : "",
				async : true,
				success : null,
				error : null
			};
			this.m_szHttpHead = "";
			this.m_szHttpContent = "";
			this.m_szHttpData = ""
		};
		nt.prototype.m_httpRequestSet = [];
		nt.prototype.setRequestParam = function(e) {
			h.extend(this.options, e)
		};
		nt.prototype.submitRequest = function() {
			var e = this.getHttpMethod(this.options.type), t = null;
			if (this.options.async) {
				var r = a.HWP_SubmitHttpRequest(e, this.options.url,
						this.options.auth, this.options.data,
						this.options.timeout);
				if (r != -1) {
					t = {
						iRequestID : r,
						funSuccessCallback : this.options.success,
						funErrorCallback : this.options.error
					};
					this.m_httpRequestSet.push(t)
				}
			} else {
				var s = a.HWP_SendHttpSynRequest(e, this.options.url,
						this.options.auth, this.options.data,
						this.options.timeout);
				t = {
					funSuccessCallback : this.options.success,
					funErrorCallback : this.options.error
				};
				this.httpDataAnalyse(t, s)
			}
		};
		nt.prototype.getHttpMethod = function(e) {
			var t = {
				GET : 1,
				POST : 2,
				PUT : 5,
				DELETE : 6
			}, r = t[e];
			return r ? r : -1
		};
		nt.prototype.processCallback = function(e, t) {
			var r = null;
			for (var s = 0; s < this.m_httpRequestSet.length; s++) {
				if (e == this.m_httpRequestSet[s].iRequestID) {
					r = this.m_httpRequestSet[s];
					this.m_httpRequestSet.splice(s, 1);
					break
				}
			}
			if (null == r) {
				return
			}
			this.httpDataAnalyse(r, t);
			delete r
		};
		nt.prototype.httpDataAnalyse = function(e, t) {
			var r = "";
			var s = 0;
			if ("" == t || E(t)) {
				e.funErrorCallback()
			} else {
				s = parseInt(t.substring(0, 3));
				r = t.substring(3, t.length);
				if (!isNaN(s)) {
					if (S == s) {
						e.funSuccessCallback(h.loadXML(r))
					} else {
						e.funErrorCallback(s, h.loadXML(r))
					}
				} else {
					e.funErrorCallback()
				}
			}
		};
		var ot = function() {
		};
		ot.prototype.CGI = {
			login : "%s%s:%s/ISAPI/Security/userCheck",
			getAudioInfo : "%s%s:%s/ISAPI/System/TwoWayAudio/channels",
			getDeviceInfo : "%s%s:%s/ISAPI/System/deviceInfo",
			getAnalogChannelInfo : "%s%s:%s/ISAPI/System/Video/inputs/channels",
			getDigitalChannel : "%s%s:%s/ISAPI/ContentMgmt/InputProxy/channels",
			getDigitalChannelInfo : "%s%s:%s/ISAPI/ContentMgmt/InputProxy/channels/status",
			getZeroChannelInfo : "%s%s:%s/ISAPI/ContentMgmt/ZeroVideo/channels",
			getStreamChannels : {
				analog : "%s%s:%s/ISAPI/Streaming/channels",
				digital : "%s%s:%s/ISAPI/ContentMgmt/StreamingProxy/channels"
			},
			getStreamDynChannels : "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/DynStreaming/channels",
			startRealPlay : {
				channels : "%s%s:%s/PSIA/streaming/channels/%s",
				zeroChannels : "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/ZeroStreaming/channels/%s"
			},
			startShttpRealPlay : {
				channels : "%s%s:%s/SDK/play/%s/004",
				zeroChannels : "%s%s:%s/SDK/play/100/004/ZeroStreaming"
			},
			startVoiceTalk : {
				open : "%s%s:%s/ISAPI/System/TwoWayAudio/channels/%s/open",
				close : "%s%s:%s/ISAPI/System/TwoWayAudio/channels/%s/close",
				audioData : "%s%s:%s/ISAPI/System/TwoWayAudio/channels/%s/audioData"
			},
			ptzControl : {
				analog : "%s%s:%s/ISAPI/PTZCtrl/channels/%s/continuous",
				digital : "%s%s:%s/ISAPI/ContentMgmt/PTZCtrlProxy/channels/%s/continuous"
			},
			ptzAutoControl : {
				ipdome : "%s%s:%s/ISAPI/PTZCtrl/channels/%s/presets/%s/goto",
				analog : "%s%s:%s/ISAPI/PTZCtrl/channels/%s/autoPan",
				digital : "%s%s:%s/ISAPI/ContentMgmt/PTZCtrlProxy/channels/%s/autoPan"
			},
			setPreset : {
				analog : "%s%s:%s/ISAPI/PTZCtrl/channels/%s/presets/%s",
				digital : "%s%s:%s/ISAPI/ContentMgmt/PTZCtrlProxy/channels/%s/presets/%s"
			},
			goPreset : {
				analog : "%s%s:%s/ISAPI/PTZCtrl/channels/%s/presets/%s/goto",
				digital : "%s%s:%s/ISAPI/ContentMgmt/PTZCtrlProxy/channels/%s/presets/%s/goto"
			},
			ptzFocus : {
				analog : "%s%s:%s/ISAPI/Image/channels/%s/focus",
				digital : "%s%s:%s/ISAPI/ContentMgmt/ImageProxy/channels/%s/focus",
				ipc : "%s%s:%s/ISAPI/System/Video/inputs/channels/%s/focus"
			},
			ptzIris : {
				analog : "%s%s:%s/ISAPI/Image/channels/%s/iris",
				digital : "%s%s:%s/ISAPI/ContentMgmt/ImageProxy/channels/%s/iris",
				ipc : "%s%s:%s/ISAPI/System/Video/inputs/channels/%s/iris"
			},
			getNetworkBond : "%s%s:%s/ISAPI/System/Network/Bond",
			getNetworkInterface : "%s%s:%s/ISAPI/System/Network/interfaces",
			getUPnPPortStatus : "%s%s:%s/ISAPI/System/Network/UPnP/ports/status",
			getPPPoEStatus : "%s%s:%s/ISAPI/System/Network/PPPoE/1/status",
			getPortInfo : "%s%s:%s/ISAPI/Security/adminAccesses",
			recordSearch : "%s%s:%s/ISAPI/ContentMgmt/search",
			startPlayback : "%s%s:%s/PSIA/streaming/tracks/%s?starttime=%s&endtime=%s",
			startShttpPlayback : "%s%s:%s/SDK/playback/%s",
			startShttpReversePlayback : "%s%s:%s/SDK/playback/%s/reversePlay",
			startTransCodePlayback : "%s%s:%s/SDK/playback/%s/transcoding",
			startDownloadRecord : "%s%s:%s/ISAPI/ContentMgmt/download",
			deviceConfig : "%s%s:%s/ISAPI/System/configurationData",
			restart : "%s%s:%s/ISAPI/System/reboot",
			restore : "%s%s:%s/ISAPI/System/factoryReset?mode=%s",
			startUpgrade : {
				upgrade : "%s%s:%s/ISAPI/System/updateFirmware",
				status : "%s%s:%s/ISAPI/System/upgradeStatus"
			},
			set3DZoom : "%s%s:%s/ISAPI/PTZCtrl/channels/%s/position3D",
			SDKCapabilities : "%s%s:%s/SDK/capabilities"
		};
		ot.prototype.login = function(e, t, s, n) {
			var o = n.protocol == 2 ? "https://" : "http://";
			var i = _(this.CGI.login, o, e, t);
			var a = new nt;
			var u = {
				type : "GET",
				url : i,
				auth : s,
				success : null,
				error : null
			};
			h.extend(u, n);
			h.extend(u, {
				success : function(e) {
					if ("200" == r.$XML(e).find("statusValue").eq(0).text()
							|| "OK" == r.$XML(e).find("statusString").eq(0)
									.text()) {
						if (n.success) {
							n.success(e)
						}
					} else {
						if (n.error) {
							n.error(401, e)
						}
					}
				},
				error : function(e, t) {
					if (n.error) {
						n.error(e, t)
					}
				}
			});
			a.setRequestParam(u);
			a.submitRequest()
		};
		ot.prototype.getAudioInfo = function(e, t) {
			var r = _(this.CGI.getAudioInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		ot.prototype.getDeviceInfo = function(e, t) {
			var s = _(this.CGI.getDeviceInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var n = new nt;
			var o = {
				type : "GET",
				url : s,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(o, t);
			h.extend(o, {
				success : function(e) {
					var s = [];
					s.push("<DeviceInfo>");
					s.push("<deviceName>"
							+ h.escape(r.$XML(e).find("deviceName").eq(0)
									.text()) + "</deviceName>");
					s.push("<deviceID>"
							+ r.$XML(e).find("deviceID").eq(0).text()
							+ "</deviceID>");
					s.push("<deviceType>"
							+ r.$XML(e).find("deviceType").eq(0).text()
							+ "</deviceType>");
					s.push("<model>" + r.$XML(e).find("model").eq(0).text()
							+ "</model>");
					s.push("<serialNumber>"
							+ r.$XML(e).find("serialNumber").eq(0).text()
							+ "</serialNumber>");
					s.push("<macAddress>"
							+ r.$XML(e).find("macAddress").eq(0).text()
							+ "</macAddress>");
					s.push("<firmwareVersion>"
							+ r.$XML(e).find("firmwareVersion").eq(0).text()
							+ "</firmwareVersion>");
					s.push("<firmwareReleasedDate>"
							+ r.$XML(e).find("firmwareReleasedDate").eq(0)
									.text() + "</firmwareReleasedDate>");
					s.push("<encoderVersion>"
							+ r.$XML(e).find("encoderVersion").eq(0).text()
							+ "</encoderVersion>");
					s.push("<encoderReleasedDate>"
							+ r.$XML(e).find("encoderReleasedDate").eq(0)
									.text() + "</encoderReleasedDate>");
					s.push("</DeviceInfo>");
					e = h.loadXML(s.join(""));
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			n.setRequestParam(o);
			n.submitRequest()
		};
		ot.prototype.getAnalogChannelInfo = function(e, t) {
			var s = _(this.CGI.getAnalogChannelInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var n = new nt;
			var o = {
				type : "GET",
				url : s,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(o, t);
			h.extend(o, {
				success : function(e) {
					var s = [];
					s.push("<VideoInputChannelList>");
					var n = r.$XML(e).find("VideoInputChannel", true);
					for (var o = 0, i = n.length; o < i; o++) {
						var a = n[o];
						s.push("<VideoInputChannel>");
						s.push("<id>" + r.$XML(a).find("id").eq(0).text()
								+ "</id>");
						s.push("<inputPort>"
								+ r.$XML(a).find("inputPort").eq(0).text()
								+ "</inputPort>");
						s.push("<name>"
								+ h.escape(r.$XML(a).find("name").eq(0).text())
								+ "</name>");
						s.push("<videoFormat>"
								+ r.$XML(a).find("videoFormat").eq(0).text()
								+ "</videoFormat>");
						s.push("</VideoInputChannel>")
					}
					s.push("</VideoInputChannelList>");
					e = h.loadXML(s.join(""));
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			n.setRequestParam(o);
			n.submitRequest()
		};
		ot.prototype.getDigitalChannel = function(e, t) {
			var s = _(this.CGI.getDigitalChannel, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var n = new nt;
			var o = {
				type : "GET",
				url : s,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(o, t);
			h.extend(o, {
				success : function(e) {
					var s = [];
					s.push("<InputProxyChannelList>");
					var n = r.$XML(e).find("InputProxyChannel", true);
					for (var o = 0, i = n.length; o < i; o++) {
						var a = n[o];
						s.push("<InputProxyChannel>");
						s.push("<id>" + r.$XML(a).find("id").eq(0).text()
								+ "</id>");
						s.push("<name>"
								+ h.escape(r.$XML(a).find("name").eq(0).text())
								+ "</name>");
						s.push("</InputProxyChannel>")
					}
					s.push("</InputProxyChannelList>");
					e = h.loadXML(s.join(""));
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			n.setRequestParam(o);
			n.submitRequest()
		};
		ot.prototype.getDigitalChannelInfo = function(e, t) {
			var s = null, n = {};
			this.getDigitalChannel(e,
					{
						async : false,
						success : function(e) {
							s = e;
							var t = r.$XML(s).find("InputProxyChannel", true);
							for (var o = 0, i = t.length; o < i; o++) {
								var a = t[o], u = r.$XML(a).find("id").eq(0)
										.text(), c = r.$XML(a).find("name").eq(
										0).text();
								n[u] = c
							}
						},
						error : function(e, r) {
							if (t.error) {
								t.error(e, r)
							}
						}
					});
			if (null === s) {
				return
			}
			var o = _(this.CGI.getDigitalChannelInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var i = new nt;
			var a = {
				type : "GET",
				url : o,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(a, t);
			h.extend(a, {
				success : function(e) {
					var s = [];
					s.push("<InputProxyChannelStatusList>");
					var o = r.$XML(e).find("InputProxyChannelStatus", true);
					for (var i = 0, a = o.length; i < a; i++) {
						var u = o[i], c = r.$XML(u).find("id").eq(0).text();
						s.push("<InputProxyChannelStatus>");
						s.push("<id>" + c + "</id>");
						s.push("<sourceInputPortDescriptor>");
						s.push("<proxyProtocol>"
								+ r.$XML(u).find("proxyProtocol").eq(0).text()
								+ "</proxyProtocol>");
						s.push("<addressingFormatType>"
								+ r.$XML(u).find("addressingFormatType").eq(0)
										.text() + "</addressingFormatType>");
						s.push("<ipAddress>"
								+ r.$XML(u).find("ipAddress").eq(0).text()
								+ "</ipAddress>");
						s.push("<managePortNo>"
								+ r.$XML(u).find("managePortNo").eq(0).text()
								+ "</managePortNo>");
						s.push("<srcInputPort>"
								+ r.$XML(u).find("srcInputPort").eq(0).text()
								+ "</srcInputPort>");
						s.push("<userName>"
								+ h.escape(r.$XML(u).find("userName").eq(0)
										.text()) + "</userName>");
						s.push("<streamType>"
								+ r.$XML(u).find("streamType").eq(0).text()
								+ "</streamType>");
						s.push("<online>"
								+ r.$XML(u).find("online").eq(0).text()
								+ "</online>");
						s.push("<name>" + h.escape(n[c]) + "</name>");
						s.push("</sourceInputPortDescriptor>");
						s.push("</InputProxyChannelStatus>")
					}
					s.push("</InputProxyChannelStatusList>");
					e = h.loadXML(s.join(""));
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			i.setRequestParam(a);
			i.submitRequest()
		};
		ot.prototype.getZeroChannelInfo = function(e, t) {
			var r = _(this.CGI.getZeroChannelInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		ot.prototype.getStreamChannels = function(e, t) {
			if (e.iAnalogChannelNum != 0) {
				var r = _(this.CGI.getStreamChannels.analog, e.szHttpProtocol,
						e.szIP, e.iCGIPort)
			} else {
				var r = _(this.CGI.getStreamChannels.digital, e.szHttpProtocol,
						e.szIP, e.iCGIPort)
			}
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		ot.prototype.getPPPoEStatus = function(e, t) {
			var r = _(this.CGI.getPPPoEStatus, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		ot.prototype.getUPnPPortStatus = function(e, t) {
			var r = _(this.CGI.getUPnPPortStatus, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		ot.prototype.getNetworkBond = function(e, t) {
			var r = _(this.CGI.getNetworkBond, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		ot.prototype.getNetworkInterface = function(e, t) {
			var r = _(this.CGI.getNetworkInterface, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		ot.prototype.getPortInfo = function(e, t) {
			var r = _(this.CGI.getPortInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		ot.prototype.startRealPlay = function(e, t) {
			var r = t.iChannelID * 100 + t.iStreamType, s = "";
			if (t.bZeroChannel) {
				s = _(t.cgi.zeroChannels, t.urlProtocol, e.szIP, t.iPort, r)
			} else {
				s = _(t.cgi.channels, t.urlProtocol, e.szIP, t.iPort, r)
			}
			var n = a.HWP_Play(s, e.szAuth, t.iWndIndex, "", "");
			if (0 == n) {
				var o = new st;
				o.iIndex = t.iWndIndex;
				o.szIP = e.szIP;
				o.iChannelID = t.iChannelID;
				o.iPlayStatus = x;
				l.push(o)
			}
			return n
		};
		ot.prototype.startVoiceTalk = function(e, t) {
			var r = _(this.CGI.startVoiceTalk.open, e.szHttpProtocol, e.szIP,
					e.iCGIPort, t), s = _(this.CGI.startVoiceTalk.close,
					e.szHttpProtocol, e.szIP, e.iCGIPort, t), n = _(
					this.CGI.startVoiceTalk.audioData, e.szHttpProtocol,
					e.szIP, e.iCGIPort, t);
			var o = a.HWP_StartVoiceTalk(r, s, n, e.szAuth, e.iAudioType);
			return o
		};
		ot.prototype.ptzAutoControl = function(e, t, r, s) {
			var n = r.iChannelID, o = "", i = "";
			s.iPTZSpeed = s.iPTZSpeed < 7 ? s.iPTZSpeed * 15 : 100;
			if (t) {
				s.iPTZSpeed = 0
			}
			if (e.szDeviceType != w) {
				if (n <= e.iAnalogChannelNum) {
					o = _(this.CGI.ptzAutoControl.analog, e.szHttpProtocol,
							e.szIP, e.iCGIPort, r.iChannelID)
				} else {
					if (r.bShttpIPChannel) {
						o = _(this.CGI.ptzAutoControl.digital,
								e.szHttpProtocol, e.szIP, e.iCGIPort,
								r.iChannelID - e.oStreamCapa.iIpChanBase + 1
										+ e.iAnalogChannelNum)
					} else {
						o = _(this.CGI.ptzAutoControl.digital,
								e.szHttpProtocol, e.szIP, e.iCGIPort,
								r.iChannelID)
					}
				}
				i = "<?xml version='1.0' encoding='UTF-8'?>" + "<autoPanData>"
						+ "<autoPan>" + s.iPTZSpeed + "</autoPan>"
						+ "</autoPanData>"
			} else {
				var a = 99;
				if (t) {
					a = 96
				}
				o = _(this.CGI.ptzAutoControl.ipdome, e.szHttpProtocol, e.szIP,
						e.iCGIPort, r.iChannelID, a)
			}
			var u = new nt;
			var c = {
				type : "PUT",
				url : o,
				async : false,
				auth : e.szAuth,
				data : i,
				success : null,
				error : null
			};
			var l = this;
			h.extend(c, s);
			h.extend(c, {
				success : function(e) {
					r.bPTZAuto = !r.bPTZAuto;
					if (s.success) {
						s.success(e)
					}
				},
				error : function(t, n) {
					if (H == e.szDeviceType || Z == e.szDeviceType) {
						if (r.bShttpIPChannel) {
							o = _(l.CGI.ptzControl.analog, e.szHttpProtocol,
									e.szIP, e.iCGIPort, r.iChannelID
											- e.oStreamCapa.iIpChanBase + 1
											+ e.iAnalogChannelNum)
						} else {
							o = _(l.CGI.ptzControl.analog, e.szHttpProtocol,
									e.szIP, e.iCGIPort, r.iChannelID)
						}
						i = "<?xml version='1.0' encoding='UTF-8'?>"
								+ "<PTZData>" + "<pan>" + s.iPTZSpeed
								+ "</pan>" + "<tilt>" + 0 + "</tilt>"
								+ "</PTZData>";
						var a = new nt;
						var u = {
							type : "PUT",
							url : o,
							async : false,
							auth : e.szAuth,
							data : i,
							success : null,
							error : null
						};
						h.extend(u, s);
						a.setRequestParam(u);
						a.submitRequest()
					} else {
						if (s.error) {
							s.error(t, n)
						}
					}
				}
			});
			u.setRequestParam(c);
			u.submitRequest()
		};
		ot.prototype.ptzControl = function(e, t, r, s) {
			var n = r.iChannelID, o = "";
			if (r.bPTZAuto) {
				this.ptzAutoControl(e, true, r, {
					iPTZSpeed : 0
				})
			}
			if (t) {
				s.iPTZSpeed = 0
			} else {
				s.iPTZSpeed = s.iPTZSpeed < 7 ? s.iPTZSpeed * 15 : 100
			}
			var i = [ {}, {
				pan : 0,
				tilt : s.iPTZSpeed
			}, {
				pan : 0,
				tilt : -s.iPTZSpeed
			}, {
				pan : -s.iPTZSpeed,
				tilt : 0
			}, {
				pan : s.iPTZSpeed,
				tilt : 0
			}, {
				pan : -s.iPTZSpeed,
				tilt : s.iPTZSpeed
			}, {
				pan : -s.iPTZSpeed,
				tilt : -s.iPTZSpeed
			}, {
				pan : s.iPTZSpeed,
				tilt : s.iPTZSpeed
			}, {
				pan : s.iPTZSpeed,
				tilt : -s.iPTZSpeed
			}, {}, {
				speed : s.iPTZSpeed
			}, {
				speed : -s.iPTZSpeed
			}, {
				speed : s.iPTZSpeed
			}, {
				speed : -s.iPTZSpeed
			}, {
				speed : s.iPTZSpeed
			}, {
				speed : -s.iPTZSpeed
			} ];
			var a = "";
			var u = {};
			switch (s.iPTZIndex) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				u = this.CGI.ptzControl;
				a = "<?xml version='1.0' encoding='UTF-8'?>" + "<PTZData>"
						+ "<pan>" + i[s.iPTZIndex].pan + "</pan>" + "<tilt>"
						+ i[s.iPTZIndex].tilt + "</tilt>" + "</PTZData>";
				break;
			case 10:
			case 11:
				u = this.CGI.ptzControl;
				a = "<?xml version='1.0' encoding='UTF-8'?>" + "<PTZData>"
						+ "<zoom>" + i[s.iPTZIndex].speed + "</zoom>"
						+ "</PTZData>";
				break;
			case 12:
			case 13:
				u = this.CGI.ptzFocus;
				a = "<?xml version='1.0' encoding='UTF-8'?>" + "<FocusData>"
						+ "<focus>" + i[s.iPTZIndex].speed + "</focus>"
						+ "</FocusData>";
				break;
			case 14:
			case 15:
				u = this.CGI.ptzIris;
				a = "<?xml version='1.0' encoding='UTF-8'?>" + "<IrisData>"
						+ "<iris>" + i[s.iPTZIndex].speed + "</iris>"
						+ "</IrisData>";
				break;
			default:
				if (E(s.error)) {
					s.error()
				}
				return
			}
			if ((u == this.CGI.ptzFocus || u == this.CGI.ptzIris)
					&& (e.szDeviceType == H || e.szDeviceType == w || e.szDeviceType == Z)) {
				o = _(u.ipc, e.szHttpProtocol, e.szIP, e.iCGIPort, r.iChannelID)
			} else {
				if (n <= e.iAnalogChannelNum) {
					o = _(u.analog, e.szHttpProtocol, e.szIP, e.iCGIPort,
							r.iChannelID)
				} else {
					if (r.bShttpIPChannel) {
						o = _(u.digital, e.szHttpProtocol, e.szIP, e.iCGIPort,
								r.iChannelID - e.oStreamCapa.iIpChanBase + 1
										+ e.iAnalogChannelNum)
					} else {
						o = _(u.digital, e.szHttpProtocol, e.szIP, e.iCGIPort,
								r.iChannelID)
					}
				}
			}
			var c = new nt;
			var l = {
				type : "PUT",
				url : o,
				async : false,
				auth : e.szAuth,
				data : a,
				success : null,
				error : null
			};
			h.extend(l, s);
			h.extend(l, {
				success : function(e) {
					if (s.success) {
						s.success(e)
					}
				},
				error : function(e, t) {
					if (s.error) {
						s.error(e, t)
					}
				}
			});
			c.setRequestParam(l);
			c.submitRequest()
		};
		ot.prototype.setPreset = function(e, t, r) {
			var s = t.iChannelID, n = "", o = "";
			if (s <= e.iAnalogChannelNum) {
				n = _(this.CGI.setPreset.analog, e.szHttpProtocol, e.szIP,
						e.iCGIPort, t.iChannelID, r.iPresetID)
			} else {
				if (t.bShttpIPChannel) {
					n = _(this.CGI.setPreset.digital, e.szHttpProtocol, e.szIP,
							e.iCGIPort, t.iChannelID
									- e.oStreamCapa.iIpChanBase + 1
									+ e.iAnalogChannelNum, r.iPresetID)
				} else {
					n = _(this.CGI.setPreset.digital, e.szHttpProtocol, e.szIP,
							e.iCGIPort, t.iChannelID, r.iPresetID)
				}
			}
			o = "<?xml version='1.0' encoding='UTF-8'?>";
			o += "<PTZPreset>";
			o += "<id>" + r.iPresetID + "</id>";
			if (e.szDeviceType != w) {
				o += "<presetName>" + "Preset" + r.iPresetID + "</presetName>"
			}
			o += "</PTZPreset>";
			var i = new nt;
			var a = {
				type : "PUT",
				url : n,
				auth : e.szAuth,
				data : o,
				success : null,
				error : null
			};
			h.extend(a, r);
			h.extend(a, {
				success : function(e) {
					if (r.success) {
						r.success(e)
					}
				},
				error : function(e, t) {
					if (r.error) {
						r.error(e, t)
					}
				}
			});
			i.setRequestParam(a);
			i.submitRequest()
		};
		ot.prototype.goPreset = function(e, t, r) {
			var s = t.iChannelID, n = "";
			if (s <= e.iAnalogChannelNum) {
				n = _(this.CGI.goPreset.analog, e.szHttpProtocol, e.szIP,
						e.iCGIPort, t.iChannelID, r.iPresetID)
			} else {
				if (t.bShttpIPChannel) {
					n = _(this.CGI.goPreset.digital, e.szHttpProtocol, e.szIP,
							e.iCGIPort, t.iChannelID
									- e.oStreamCapa.iIpChanBase + 1
									+ e.iAnalogChannelNum, r.iPresetID)
				} else {
					n = _(this.CGI.goPreset.digital, e.szHttpProtocol, e.szIP,
							e.iCGIPort, t.iChannelID, r.iPresetID)
				}
			}
			var o = new nt;
			var i = {
				type : "PUT",
				url : n,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(i, r);
			h.extend(i, {
				success : function(e) {
					if (r.success) {
						r.success(e)
					}
				},
				error : function(e, t) {
					if (r.error) {
						r.error(e, t)
					}
				}
			});
			o.setRequestParam(i);
			o.submitRequest()
		};
		ot.prototype.recordSearch = function(e, t) {
			var s = "", n = "", o = t.iChannelID, i = t.szStartTime.replace(
					" ", "T")
					+ "Z", a = t.szEndTime.replace(" ", "T") + "Z";
			s = _(this.CGI.recordSearch, e.szHttpProtocol, e.szIP, e.iCGIPort);
			n = "<?xml version='1.0' encoding='UTF-8'?>"
					+ "<CMSearchDescription>" + "<searchID>"
					+ new ct
					+ "</searchID>"
					+ "<trackList><trackID>"
					+ (o * 100 + 1)
					+ "</trackID></trackList>"
					+ "<timeSpanList>"
					+ "<timeSpan>"
					+ "<startTime>"
					+ i
					+ "</startTime>"
					+ "<endTime>"
					+ a
					+ "</endTime>"
					+ "</timeSpan>"
					+ "</timeSpanList>"
					+ "<maxResults>40</maxResults>"
					+ "<searchResultPostion>"
					+ t.iSearchPos
					+ "</searchResultPostion>"
					+ "<metadataList>"
					+ "<metadataDescriptor>//metadata.ISAPI.org/VideoMotion</metadataDescriptor>"
					+ "</metadataList>" + "</CMSearchDescription>";
			var u = new nt;
			var c = {
				type : "POST",
				url : s,
				auth : e.szAuth,
				data : n,
				success : null,
				error : null
			};
			h.extend(c, t);
			h.extend(c, {
				success : function(e) {
					var s = [];
					s.push("<CMSearchResult>");
					s.push("<responseStatus>"
							+ r.$XML(e).find("responseStatus").eq(0).text()
							+ "</responseStatus>");
					s.push("<responseStatusStrg>"
							+ r.$XML(e).find("responseStatusStrg").eq(0).text()
							+ "</responseStatusStrg>");
					s.push("<numOfMatches>"
							+ r.$XML(e).find("numOfMatches").eq(0).text()
							+ "</numOfMatches>");
					s.push("<matchList>");
					var n = r.$XML(e).find("searchMatchItem", true);
					for (var o = 0, i = n.length; o < i; o++) {
						var a = n[o];
						s.push("<searchMatchItem>");
						s.push("<trackID>"
								+ r.$XML(a).find("trackID").eq(0).text()
								+ "</trackID>");
						s.push("<startTime>"
								+ r.$XML(a).find("startTime").eq(0).text()
								+ "</startTime>");
						s.push("<endTime>"
								+ r.$XML(a).find("endTime").eq(0).text()
								+ "</endTime>");
						s.push("<playbackURI>"
								+ h.escape(r.$XML(a).find("playbackURI").eq(0)
										.text()) + "</playbackURI>");
						s.push("<metadataDescriptor>"
								+ r.$XML(a).find("metadataDescriptor").eq(0)
										.text().split("/")[1]
								+ "</metadataDescriptor>");
						s.push("</searchMatchItem>")
					}
					s.push("</matchList>");
					s.push("</CMSearchResult>");
					e = h.loadXML(s.join(""));
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			u.setRequestParam(c);
			u.submitRequest()
		};
		ot.prototype.startPlayback = function(e, t) {
			var r = t.iWndIndex, s = t.szStartTime, n = t.szEndTime;
			var o = _(t.cgi, t.urlProtocol, e.szIP, t.iPort, t.iChannelID, s, n);
			if (!E(t.oTransCodeParam)) {
				var i = et(t.oTransCodeParam);
				if (i == "") {
					return -1
				}
				a.HWP_SetTrsPlayBackParam(r, i)
			}
			var u = a.HWP_Play(o, e.szAuth, r, s, n);
			if (0 == u) {
				var c = new st;
				c.iIndex = r;
				c.szIP = e.szIP;
				c.iChannelID = t.iChannelID;
				c.iPlayStatus = T;
				l.push(c)
			}
			return u
		};
		ot.prototype.reversePlayback = function(e, t) {
			var r = t.iWndIndex, s = t.szStartTime, n = t.szEndTime;
			var o = _(t.cgi, t.urlProtocol, e.szIP, t.iPort, t.iChannelID, s, n);
			var i = a.HWP_ReversePlay(o, e.szAuth, r, s, n);
			if (0 == i) {
				var u = new st;
				u.iIndex = r;
				u.szIP = e.szIP;
				u.iChannelID = t.iChannelID;
				u.iPlayStatus = A;
				l.push(u)
			}
			return i
		};
		ot.prototype.startDownloadRecord = function(e, t) {
			var r = _(this.CGI.startDownloadRecord, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = "<?xml version='1.0' encoding='UTF-8'?>"
					+ "<downloadRequest>" + "<playbackURI> "
					+ h.escape(t.szPlaybackURI) + "</playbackURI>"
					+ "</downloadRequest>";
			return a.HWP_StartDownload(r, e.szAuth, t.szFileName, s)
		};
		ot.prototype.exportDeviceConfig = function(e) {
			var t = _(this.CGI.deviceConfig, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			return a.HWP_ExportDeviceConfig(t, e.szAuth, "", 0)
		};
		ot.prototype.importDeviceConfig = function(e, t) {
			var r = _(this.CGI.deviceConfig, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			return a.HWP_ImportDeviceConfig(r, e.szAuth, t.szFileName, 0)
		};
		ot.prototype.restart = function(e, t) {
			var r = _(this.CGI.restart, e.szHttpProtocol, e.szIP, e.iCGIPort);
			var s = new nt;
			var n = {
				type : "PUT",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		ot.prototype.restore = function(e, t, r) {
			var s = _(this.CGI.restore, e.szHttpProtocol, e.szIP, e.iCGIPort, t);
			var n = new nt;
			var o = {
				type : "PUT",
				url : s,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(o, r);
			h.extend(o, {
				success : function(e) {
					if (r.success) {
						r.success(e)
					}
				},
				error : function(e, t) {
					if (r.error) {
						r.error(e, t)
					}
				}
			});
			n.setRequestParam(o);
			n.submitRequest()
		};
		ot.prototype.startUpgrade = function(e, t) {
			var r = _(this.CGI.startUpgrade.upgrade, e.szHttpProtocol, e.szIP,
					e.iCGIPort), s = _(this.CGI.startUpgrade.status,
					e.szHttpProtocol, e.szIP, e.iCGIPort);
			return a.HWP_StartUpgrade(r, s, e.szAuth, t.szFileName)
		};
		ot.prototype.set3DZoom = function(e, t, s, n) {
			var o = t.iChannelID, i = "";
			if (o <= e.iAnalogChannelNum) {
				i = _(this.CGI.set3DZoom, e.szHttpProtocol, e.szIP, e.iCGIPort,
						t.iChannelID)
			} else {
				if (t.bShttpIPChannel) {
					i = _(this.CGI.set3DZoom, e.szHttpProtocol, e.szIP,
							e.iCGIPort, t.iChannelID
									- e.oStreamCapa.iIpChanBase + 1
									+ e.iAnalogChannelNum)
				} else {
					i = _(this.CGI.set3DZoom, e.szHttpProtocol, e.szIP,
							e.iCGIPort, t.iChannelID)
				}
			}
			var a = h.loadXML(s), u = parseInt(r.$XML(a).find("StartPoint").eq(
					0).find("positionX").eq(0).text(), 10), c = parseInt(r
					.$XML(a).find("StartPoint").eq(0).find("positionY").eq(0)
					.text(), 10), l = parseInt(r.$XML(a).find("EndPoint").eq(0)
					.find("positionX").eq(0).text(), 10), p = parseInt(r
					.$XML(a).find("EndPoint").eq(0).find("positionY").eq(0)
					.text(), 10);
			var d = "<?xml version='1.0' encoding='UTF-8'?>" + "<position3D>"
					+ "<StartPoint>" + "<positionX>" + u + "</positionX>"
					+ "<positionY>" + (255 - c) + "</positionY>"
					+ "</StartPoint>" + "<EndPoint>" + "<positionX>" + l
					+ "</positionX>" + "<positionY>" + (255 - p)
					+ "</positionY>" + "</EndPoint>" + "</position3D>";
			var f = new nt;
			var P = {
				type : "PUT",
				url : i,
				data : d,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(P, n);
			h.extend(P, {
				success : function(e) {
					if (n.success) {
						n.success(e)
					}
				},
				error : function(e, t) {
					if (n.error) {
						n.error(e, t)
					}
				}
			});
			f.setRequestParam(P);
			f.submitRequest()
		};
		ot.prototype.getSDKCapa = function(e, t) {
			var r = _(this.CGI.SDKCapabilities, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		var it = function() {
		};
		it.prototype.CGI = {
			login : "%s%s:%s/PSIA/Custom/SelfExt/userCheck",
			getAudioInfo : "%s%s:%s/PSIA/Custom/SelfExt/TwoWayAudio/channels",
			getDeviceInfo : "%s%s:%s/PSIA/System/deviceInfo",
			getAnalogChannelInfo : "%s%s:%s/PSIA/System/Video/inputs/channels",
			getDigitalChannel : "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/DynVideo/inputs/channels",
			getDigitalChannelInfo : "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/DynVideo/inputs/channels/status",
			getZeroChannelInfo : "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/ZeroVideo/channels",
			getStreamChannels : {
				analog : "%s%s:%s/PSIA/Streaming/channels",
				digital : "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/DynStreaming/channels"
			},
			getStreamDynChannels : "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/DynStreaming/channels",
			startRealPlay : {
				channels : "%s%s:%s/PSIA/streaming/channels/%s",
				zeroChannels : "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/ZeroStreaming/channels/%s"
			},
			startVoiceTalk : {
				open : "%s%s:%s/PSIA/Custom/SelfExt/TwoWayAudio/channels/%s/open",
				close : "%s%s:%s/PSIA/Custom/SelfExt/TwoWayAudio/channels/%s/close",
				audioData : "%s%s:%s/PSIA/Custom/SelfExt/TwoWayAudio/channels/%s/audioData"
			},
			ptzControl : "%s%s:%s/PSIA/PTZ/channels/%s/continuous",
			ptzAutoControl : "%s%s:%s/PSIA/Custom/SelfExt/PTZ/channels/%s/autoptz",
			setPreset : "%s%s:%s/PSIA/PTZ/channels/%s/presets/%s",
			goPreset : "%s%s:%s/PSIA/PTZ/channels/%s/presets/%s/goto",
			ptzFocus : "%s%s:%s/PSIA/System/Video/inputs/channels/%s/focus",
			ptzIris : "%s%s:%s/PSIA/System/Video/inputs/channels/%s/iris",
			getNetworkBond : "%s%s:%s/PSIA/Custom/SelfExt/Bond",
			getNetworkInterface : "%s%s:%s/PSIA/System/Network/interfaces",
			getUPnPPortStatus : "%s%s:%s/PSIA/Custom/SelfExt/UPnP/ports/status",
			getPPPoEStatus : "%s%s:%s/PSIA/Custom/SelfExt/PPPoE/1/status",
			getPortInfo : "%s%s:%s/PSIA/Security/AAA/adminAccesses",
			recordSearch : "%s%s:%s/PSIA/ContentMgmt/search",
			startPlayback : "%s%s:%s/PSIA/streaming/tracks/%s?starttime=%s&endtime=%s",
			startDownloadRecord : "%s%s:%s/PSIA/Custom/SelfExt/ContentMgmt/download",
			deviceConfig : "%s%s:%s/PSIA/System/configurationData",
			restart : "%s%s:%s/PSIA/System/reboot",
			restore : "%s%s:%s/PSIA/System/factoryReset?mode=%s",
			startUpgrade : {
				upgrade : "%s%s:%s/PSIA/System/updateFirmware",
				status : "%s%s:%s/PSIA/Custom/SelfExt/upgradeStatus"
			},
			set3DZoom : "%s%s:%s/PSIA/Custom/SelfExt/PTZ/channels/%s/Set3DZoom"
		};
		it.prototype.login = function(e, t, s, n) {
			var o = n.protocol == 2 ? "https://" : "http://";
			var i = _(this.CGI.login, o, e, t);
			var a = new nt;
			var u = {
				type : "GET",
				url : i,
				auth : s,
				success : null,
				error : null
			};
			h.extend(u, n);
			h.extend(u, {
				success : function(e) {
					if ("200" == r.$XML(e).find("statusValue").eq(0).text()) {
						if (n.success) {
							n.success(e)
						}
					} else {
						if (n.error) {
							n.error(401, e)
						}
					}
				},
				error : function(e, t) {
					if (n.error) {
						n.error(e, t)
					}
				}
			});
			a.setRequestParam(u);
			a.submitRequest()
		};
		it.prototype.getAudioInfo = function(e, t) {
			var r = _(this.CGI.getAudioInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		it.prototype.getDeviceInfo = function(e, t) {
			var s = _(this.CGI.getDeviceInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var n = new nt;
			var o = {
				type : "GET",
				url : s,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(o, t);
			h.extend(o, {
				success : function(e) {
					var s = [];
					s.push("<DeviceInfo>");
					s.push("<deviceName>"
							+ h.escape(r.$XML(e).find("deviceName").eq(0)
									.text()) + "</deviceName>");
					s.push("<deviceID>"
							+ r.$XML(e).find("deviceID").eq(0).text()
							+ "</deviceID>");
					s.push("<deviceType>"
							+ r.$XML(e).find("deviceDescription").eq(0).text()
							+ "</deviceType>");
					s.push("<model>" + r.$XML(e).find("model").eq(0).text()
							+ "</model>");
					s.push("<serialNumber>"
							+ r.$XML(e).find("serialNumber").eq(0).text()
							+ "</serialNumber>");
					s.push("<macAddress>"
							+ r.$XML(e).find("macAddress").eq(0).text()
							+ "</macAddress>");
					s.push("<firmwareVersion>"
							+ r.$XML(e).find("firmwareVersion").eq(0).text()
							+ "</firmwareVersion>");
					s.push("<firmwareReleasedDate>"
							+ r.$XML(e).find("firmwareReleasedDate").eq(0)
									.text() + "</firmwareReleasedDate>");
					s.push("<encoderVersion>"
							+ r.$XML(e).find("logicVersion").eq(0).text()
							+ "</encoderVersion>");
					s.push("<encoderReleasedDate>"
							+ r.$XML(e).find("logicReleasedDate").eq(0).text()
							+ "</encoderReleasedDate>");
					s.push("</DeviceInfo>");
					e = h.loadXML(s.join(""));
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			n.setRequestParam(o);
			n.submitRequest()
		};
		it.prototype.getAnalogChannelInfo = function(e, t) {
			var s = _(this.CGI.getAnalogChannelInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var n = new nt;
			var o = {
				type : "GET",
				url : s,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(o, t);
			h.extend(o, {
				success : function(e) {
					var s = [];
					s.push("<VideoInputChannelList>");
					var n = r.$XML(e).find("VideoInputChannel", true);
					for (var o = 0, i = n.length; o < i; o++) {
						var a = n[o];
						s.push("<VideoInputChannel>");
						s.push("<id>" + r.$XML(a).find("id").eq(0).text()
								+ "</id>");
						s.push("<inputPort>"
								+ r.$XML(a).find("inputPort").eq(0).text()
								+ "</inputPort>");
						s.push("<name>"
								+ h.escape(r.$XML(a).find("name").eq(0).text())
								+ "</name>");
						s.push("<videoFormat>"
								+ r.$XML(a).find("videoFormat").eq(0).text()
								+ "</videoFormat>");
						s.push("</VideoInputChannel>")
					}
					s.push("</VideoInputChannelList>");
					e = h.loadXML(s.join(""));
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			n.setRequestParam(o);
			n.submitRequest()
		};
		it.prototype.getDigitalChannel = function(e, t) {
			var s = _(this.CGI.getDigitalChannel, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var n = new nt;
			var o = {
				type : "GET",
				url : s,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(o, t);
			h.extend(o, {
				success : function(e) {
					var s = [];
					s.push("<InputProxyChannelList>");
					var n = r.$XML(e).find("DynVideoInputChannel", true);
					for (var o = 0, i = n.length; o < i; o++) {
						var a = n[o];
						s.push("<InputProxyChannel>");
						s.push("<id>" + r.$XML(a).find("id").eq(0).text()
								+ "</id>");
						s.push("<name>"
								+ h.escape(r.$XML(a).find("name").eq(0).text())
								+ "</name>");
						s.push("</InputProxyChannel>")
					}
					s.push("</InputProxyChannelList>");
					e = h.loadXML(s.join(""));
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			n.setRequestParam(o);
			n.submitRequest()
		};
		it.prototype.getDigitalChannelInfo = function(e, t) {
			var s = null, n = {};
			this.getDigitalChannel(e,
					{
						async : false,
						success : function(e) {
							s = e;
							var t = r.$XML(s).find("InputProxyChannel", true);
							for (var o = 0, i = t.length; o < i; o++) {
								var a = t[o], u = r.$XML(a).find("id").eq(0)
										.text(), c = r.$XML(a).find("name").eq(
										0).text();
								n[u] = c
							}
						},
						error : function(e, r) {
							if (t.error) {
								t.error(e, r)
							}
						}
					});
			if (null === s) {
				return
			}
			var o = _(this.CGI.getDigitalChannelInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var i = new nt;
			var a = {
				type : "GET",
				url : o,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(a, t);
			h.extend(a, {
				success : function(e) {
					var s = [];
					s.push("<InputProxyChannelStatusList>");
					var o = r.$XML(e).find("DynVideoInputChannelStatus", true);
					for (var i = 0, a = o.length; i < a; i++) {
						var u = o[i], c = r.$XML(u).find("id").eq(0).text();
						s.push("<InputProxyChannelStatus>");
						s.push("<id>" + c + "</id>");
						s.push("<sourceInputPortDescriptor>");
						s.push("<proxyProtocol>"
								+ r.$XML(u).find("adminProtocol").eq(0).text()
								+ "</proxyProtocol>");
						s.push("<addressingFormatType>"
								+ r.$XML(u).find("addressingFormatType").eq(0)
										.text() + "</addressingFormatType>");
						s.push("<ipAddress>"
								+ r.$XML(u).find("ipAddress").eq(0).text()
								+ "</ipAddress>");
						s.push("<managePortNo>"
								+ r.$XML(u).find("adminPortNo").eq(0).text()
								+ "</managePortNo>");
						s.push("<srcInputPort>"
								+ r.$XML(u).find("srcInputPort").eq(0).text()
								+ "</srcInputPort>");
						s.push("<userName>"
								+ h.escape(r.$XML(u).find("userName").eq(0)
										.text()) + "</userName>");
						s.push("<streamType>"
								+ r.$XML(u).find("streamType").eq(0).text()
								+ "</streamType>");
						s.push("<online>"
								+ r.$XML(u).find("online").eq(0).text()
								+ "</online>");
						s.push("<name>" + h.escape(n[c]) + "</name>");
						s.push("</sourceInputPortDescriptor>");
						s.push("</InputProxyChannelStatus>")
					}
					s.push("</InputProxyChannelStatusList>");
					e = h.loadXML(s.join(""));
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			i.setRequestParam(a);
			i.submitRequest()
		};
		it.prototype.getZeroChannelInfo = function(e, t) {
			var r = _(this.CGI.getZeroChannelInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		it.prototype.getPPPoEStatus = function(e, t) {
			var r = _(this.CGI.getPPPoEStatus, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		it.prototype.getUPnPPortStatus = function(e, t) {
			var r = _(this.CGI.getUPnPPortStatus, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		it.prototype.getNetworkBond = function(e, t) {
			var r = _(this.CGI.getNetworkBond, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		it.prototype.getNetworkInterface = function(e, t) {
			var r = _(this.CGI.getNetworkInterface, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		it.prototype.getPortInfo = function(e, t) {
			var s = _(this.CGI.getPortInfo, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var n = new nt;
			var o = {
				type : "GET",
				url : s,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(o, t);
			h
					.extend(
							o,
							{
								success : function(s) {
									var n = [];
									n.push("<AdminAccessProtocolList>");
									var o = r.$XML(s).find(
											"AdminAccessProtocol", true);
									for (var i = 0, a = o.length; i < a; i++) {
										var u = o[i];
										n.push("<AdminAccessProtocol>");
										n.push("<id>"
												+ r.$XML(s).find("id").eq(0)
														.text() + "</id>");
										n.push("<enabled>"
												+ r.$XML(s).find("enabled").eq(
														0).text()
												+ "</enabled>");
										n.push("<protocol>"
												+ r.$XML(s).find("protocol")
														.eq(0).text()
														.toUpperCase()
												+ "</protocol>");
										n.push("<portNo>"
												+ r.$XML(s).find("portNo")
														.eq(0).text()
												+ "</portNo>");
										n.push("</AdminAccessProtocol>")
									}
									P
											.getStreamChannels(
													e,
													{
														async : false,
														success : function(s) {
															if (r
																	.$XML(s)
																	.find(
																			"rtspPortNo",
																			true).length > 0) {
																var o = parseInt(
																		r
																				.$XML(
																						s)
																				.find(
																						"rtspPortNo")
																				.eq(
																						0)
																				.text(),
																		10);
																n
																		.push("<AdminAccessProtocol>");
																n
																		.push("<id>"
																				+ 4
																				+ "</id>");
																n
																		.push("<enabled>"
																				+ "true"
																				+ "</enabled>");
																n
																		.push("<protocol>"
																				+ "RTSP"
																				+ "</protocol>");
																n
																		.push("<portNo>"
																				+ o
																				+ "</portNo>");
																n
																		.push("</AdminAccessProtocol>");
																n
																		.push("</AdminAccessProtocolList>");
																var i = h
																		.loadXML(n
																				.join(""));
																if (t.success) {
																	t
																			.success(i)
																}
															} else {
																P
																		.getStreamDynChannels(
																				e,
																				{
																					async : false,
																					success : function(
																							e) {
																						if (r
																								.$XML(
																										e)
																								.find(
																										"rtspPortNo",
																										true).length > 0) {
																							var s = parseInt(
																									r
																											.$XML(
																													e)
																											.find(
																													"rtspPortNo")
																											.eq(
																													0)
																											.text(),
																									10);
																							n
																									.push("<AdminAccessProtocol>");
																							n
																									.push("<id>"
																											+ 4
																											+ "</id>");
																							n
																									.push("<enabled>"
																											+ "true"
																											+ "</enabled>");
																							n
																									.push("<protocol>"
																											+ "RTSP"
																											+ "</protocol>");
																							n
																									.push("<portNo>"
																											+ s
																											+ "</portNo>");
																							n
																									.push("</AdminAccessProtocol>");
																							n
																									.push("</AdminAccessProtocolList>");
																							var o = h
																									.loadXML(n
																											.join(""));
																							if (t.success) {
																								t
																										.success(o)
																							}
																						}
																					},
																					error : function() {
																						if (t.error) {
																							t
																									.error()
																						}
																					}
																				})
															}
														},
														error : function() {
															if (t.error) {
																t.error()
															}
														}
													})
								},
								error : function() {
									var s = [];
									s.push("<AdminAccessProtocolList>");
									P
											.getStreamChannels(
													e,
													{
														async : false,
														success : function(n) {
															if (r
																	.$XML(n)
																	.find(
																			"rtspPortNo",
																			true).length > 0) {
																var o = parseInt(
																		r
																				.$XML(
																						n)
																				.find(
																						"rtspPortNo")
																				.eq(
																						0)
																				.text(),
																		10);
																s
																		.push("<AdminAccessProtocol>");
																s
																		.push("<id>"
																				+ 4
																				+ "</id>");
																s
																		.push("<enabled>"
																				+ "true"
																				+ "</enabled>");
																s
																		.push("<protocol>"
																				+ "RTSP"
																				+ "</protocol>");
																s
																		.push("<portNo>"
																				+ o
																				+ "</portNo>");
																s
																		.push("</AdminAccessProtocol>");
																s
																		.push("</AdminAccessProtocolList>");
																var i = h
																		.loadXML(s
																				.join(""));
																if (t.success) {
																	t
																			.success(i)
																}
															} else {
																P
																		.getStreamDynChannels(
																				e,
																				{
																					async : false,
																					success : function(
																							e) {
																						if (r
																								.$XML(
																										e)
																								.find(
																										"rtspPortNo",
																										true).length > 0) {
																							var n = parseInt(
																									r
																											.$XML(
																													e)
																											.find(
																													"rtspPortNo")
																											.eq(
																													0)
																											.text(),
																									10);
																							s
																									.push("<AdminAccessProtocol>");
																							s
																									.push("<id>"
																											+ 4
																											+ "</id>");
																							s
																									.push("<enabled>"
																											+ "true"
																											+ "</enabled>");
																							s
																									.push("<protocol>"
																											+ "RTSP"
																											+ "</protocol>");
																							s
																									.push("<portNo>"
																											+ n
																											+ "</portNo>");
																							s
																									.push("</AdminAccessProtocol>");
																							s
																									.push("</AdminAccessProtocolList>");
																							var o = h
																									.loadXML(s
																											.join(""));
																							if (t.success) {
																								t
																										.success(o)
																							}
																						}
																					},
																					error : function() {
																						if (t.error) {
																							t
																									.error()
																						}
																					}
																				})
															}
														},
														error : function() {
															if (t.error) {
																t.error()
															}
														}
													})
								}
							});
			n.setRequestParam(o);
			n.submitRequest()
		};
		it.prototype.getStreamChannels = function(e, t) {
			if (e.iAnalogChannelNum != 0) {
				var r = _(this.CGI.getStreamChannels.analog, e.szHttpProtocol,
						e.szIP, e.iCGIPort)
			} else {
				var r = _(this.CGI.getStreamChannels.digital, e.szHttpProtocol,
						e.szIP, e.iCGIPort)
			}
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		it.prototype.getStreamDynChannels = function(e, t) {
			var r = _(this.CGI.getStreamDynChannels, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = new nt;
			var n = {
				type : "GET",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		it.prototype.startRealPlay = function(e, t) {
			var r = t.iChannelID * 100 + t.iStreamType, s = "";
			if (t.bZeroChannel) {
				s = _(t.cgi.zeroChannels, t.urlProtocol, e.szIP, t.iPort, r)
			} else {
				s = _(t.cgi.channels, t.urlProtocol, e.szIP, t.iPort, r)
			}
			var n = a.HWP_Play(s, e.szAuth, t.iWndIndex, "", "");
			if (0 == n) {
				var o = new st;
				o.iIndex = t.iWndIndex;
				o.szIP = e.szIP;
				o.iChannelID = t.iChannelID;
				o.iPlayStatus = x;
				l.push(o)
			}
			return n
		};
		it.prototype.startVoiceTalk = function(e, t) {
			var r = _(this.CGI.startVoiceTalk.open, e.szHttpProtocol, e.szIP,
					e.iCGIPort, t), s = _(this.CGI.startVoiceTalk.close,
					e.szHttpProtocol, e.szIP, e.iCGIPort, t), n = _(
					this.CGI.startVoiceTalk.audioData, e.szHttpProtocol,
					e.szIP, e.iCGIPort, t);
			var o = a.HWP_StartVoiceTalk(r, s, n, e.szAuth, e.iAudioType);
			return o
		};
		it.prototype.ptzAutoControl = function(e, t, r, s) {
			var n = r.iChannelID, o = "", i = "";
			s.iPTZSpeed = s.iPTZSpeed < 7 ? s.iPTZSpeed * 15 : 100;
			if (t) {
				s.iPTZSpeed = 0
			}
			if (e.szDeviceType != w) {
				o = _(this.CGI.ptzAutoControl, e.szHttpProtocol, e.szIP,
						e.iCGIPort, n);
				i = "<?xml version='1.0' encoding='UTF-8'?>" + "<PTZData>"
						+ "<pan>" + s.iPTZSpeed + "</pan>" + "<tilt>0</tilt>"
						+ "</PTZData>"
			} else {
				var a = 99;
				if (t) {
					a = 96
				}
				o = _(this.CGI.goPreset, e.szHttpProtocol, e.szIP, e.iCGIPort,
						n, a)
			}
			var u = new nt;
			var c = {
				type : "PUT",
				url : o,
				async : false,
				auth : e.szAuth,
				data : i,
				success : null,
				error : null
			};
			var l = this;
			h.extend(c, s);
			h.extend(c, {
				success : function(e) {
					r.bPTZAuto = !r.bPTZAuto;
					if (s.success) {
						s.success(e)
					}
				},
				error : function(t, n) {
					if (e.szDeviceType != w) {
						o = _(l.CGI.ptzControl, e.szHttpProtocol, e.szIP,
								e.iCGIPort, r.iChannelID);
						var a = new nt;
						var u = {
							type : "PUT",
							url : o,
							async : false,
							auth : e.szAuth,
							data : i,
							success : null,
							error : null
						};
						h.extend(u, s);
						a.setRequestParam(u);
						a.submitRequest()
					} else {
						if (s.error) {
							s.error(t, n)
						}
					}
				}
			});
			u.setRequestParam(c);
			u.submitRequest()
		};
		it.prototype.ptzControl = function(e, t, r, s) {
			var n = r.iChannelID, o = "";
			if (r.bPTZAuto) {
				this.ptzAutoControl(e, true, r, {
					iPTZSpeed : 0
				})
			}
			if (t) {
				s.iPTZSpeed = 0
			} else {
				s.iPTZSpeed = s.iPTZSpeed < 7 ? s.iPTZSpeed * 15 : 100
			}
			var i = [ {}, {
				pan : 0,
				tilt : s.iPTZSpeed
			}, {
				pan : 0,
				tilt : -s.iPTZSpeed
			}, {
				pan : -s.iPTZSpeed,
				tilt : 0
			}, {
				pan : s.iPTZSpeed,
				tilt : 0
			}, {
				pan : -s.iPTZSpeed,
				tilt : s.iPTZSpeed
			}, {
				pan : -s.iPTZSpeed,
				tilt : -s.iPTZSpeed
			}, {
				pan : s.iPTZSpeed,
				tilt : s.iPTZSpeed
			}, {
				pan : s.iPTZSpeed,
				tilt : -s.iPTZSpeed
			}, {}, {
				speed : s.iPTZSpeed
			}, {
				speed : -s.iPTZSpeed
			}, {
				speed : s.iPTZSpeed
			}, {
				speed : -s.iPTZSpeed
			}, {
				speed : s.iPTZSpeed
			}, {
				speed : -s.iPTZSpeed
			} ];
			var a = "";
			var u = {};
			switch (s.iPTZIndex) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				u = this.CGI.ptzControl;
				a = "<?xml version='1.0' encoding='UTF-8'?>" + "<PTZData>"
						+ "<pan>" + i[s.iPTZIndex].pan + "</pan>" + "<tilt>"
						+ i[s.iPTZIndex].tilt + "</tilt>" + "</PTZData>";
				break;
			case 10:
			case 11:
				u = this.CGI.ptzControl;
				a = "<?xml version='1.0' encoding='UTF-8'?>" + "<PTZData>"
						+ "<zoom>" + i[s.iPTZIndex].speed + "</zoom>"
						+ "</PTZData>";
				break;
			case 12:
			case 13:
				u = this.CGI.ptzFocus;
				a = "<?xml version='1.0' encoding='UTF-8'?>" + "<FocusData>"
						+ "<focus>" + i[s.iPTZIndex].speed + "</focus>"
						+ "</FocusData>";
				break;
			case 14:
			case 15:
				u = this.CGI.ptzIris;
				a = "<?xml version='1.0' encoding='UTF-8'?>" + "<IrisData>"
						+ "<iris>" + i[s.iPTZIndex].speed + "</iris>"
						+ "</IrisData>";
				break;
			default:
				if (E(s.error)) {
					s.error()
				}
				return
			}
			o = _(u, e.szHttpProtocol, e.szIP, e.iCGIPort, r.iChannelID);
			var c = new nt;
			var l = {
				type : "PUT",
				url : o,
				async : false,
				auth : e.szAuth,
				data : a,
				success : null,
				error : null
			};
			h.extend(l, s);
			h.extend(l, {
				success : function(e) {
					if (s.success) {
						s.success(e)
					}
				},
				error : function(e, t) {
					if (s.error) {
						s.error(e, t)
					}
				}
			});
			c.setRequestParam(l);
			c.submitRequest()
		};
		it.prototype.setPreset = function(e, t, r) {
			var s = t.iChannelID, n = "", o = "";
			n = _(this.CGI.setPreset, e.szHttpProtocol, e.szIP, e.iCGIPort,
					t.iChannelID, r.iPresetID);
			o = "<?xml version='1.0' encoding='UTF-8'?>";
			o += "<PTZPreset>";
			o += "<id>" + r.iPresetID + "</id>";
			if (e.szDeviceType != w) {
				o += "<presetName>" + "Preset" + r.iPresetID + "</presetName>"
			}
			o += "</PTZPreset>";
			var i = new nt;
			var a = {
				type : "PUT",
				url : n,
				auth : e.szAuth,
				data : o,
				success : null,
				error : null
			};
			h.extend(a, r);
			h.extend(a, {
				success : function(e) {
					if (r.success) {
						r.success(e)
					}
				},
				error : function(e, t) {
					if (r.error) {
						r.error(e, t)
					}
				}
			});
			i.setRequestParam(a);
			i.submitRequest()
		};
		it.prototype.goPreset = function(e, t, r) {
			var s = t.iChannelID, n = "";
			n = _(this.CGI.goPreset, e.szHttpProtocol, e.szIP, e.iCGIPort,
					t.iChannelID, r.iPresetID);
			var o = new nt;
			var i = {
				type : "PUT",
				url : n,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(i, r);
			h.extend(i, {
				success : function(e) {
					if (r.success) {
						r.success(e)
					}
				},
				error : function(e, t) {
					if (r.error) {
						r.error(e, t)
					}
				}
			});
			o.setRequestParam(i);
			o.submitRequest()
		};
		it.prototype.recordSearch = function(e, t) {
			var s = "", n = "", o = t.iChannelID, i = t.szStartTime.replace(
					" ", "T")
					+ "Z", a = t.szEndTime.replace(" ", "T") + "Z";
			s = _(this.CGI.recordSearch, e.szHttpProtocol, e.szIP, e.iCGIPort);
			n = "<?xml version='1.0' encoding='UTF-8'?>"
					+ "<CMSearchDescription>" + "<searchID>"
					+ new ct
					+ "</searchID>"
					+ "<trackList><trackID>"
					+ (o * 100 + 1)
					+ "</trackID></trackList>"
					+ "<timeSpanList>"
					+ "<timeSpan>"
					+ "<startTime>"
					+ i
					+ "</startTime>"
					+ "<endTime>"
					+ a
					+ "</endTime>"
					+ "</timeSpan>"
					+ "</timeSpanList>"
					+ "<maxResults>40</maxResults>"
					+ "<searchResultPostion>"
					+ t.iSearchPos
					+ "</searchResultPostion>"
					+ "<metadataList>"
					+ "<metadataDescriptor>//metadata.psia.org/VideoMotion</metadataDescriptor>"
					+ "</metadataList>" + "</CMSearchDescription>";
			var u = new nt;
			var c = {
				type : "POST",
				url : s,
				auth : e.szAuth,
				data : n,
				success : null,
				error : null
			};
			h.extend(c, t);
			h.extend(c, {
				success : function(e) {
					var s = [];
					s.push("<CMSearchResult>");
					s.push("<responseStatus>"
							+ r.$XML(e).find("responseStatus").eq(0).text()
							+ "</responseStatus>");
					s.push("<responseStatusStrg>"
							+ r.$XML(e).find("responseStatusStrg").eq(0).text()
							+ "</responseStatusStrg>");
					s.push("<numOfMatches>"
							+ r.$XML(e).find("numOfMatches").eq(0).text()
							+ "</numOfMatches>");
					s.push("<matchList>");
					var n = r.$XML(e).find("searchMatchItem", true);
					for (var o = 0, i = n.length; o < i; o++) {
						var a = n[o];
						s.push("<searchMatchItem>");
						s.push("<trackID>"
								+ r.$XML(a).find("trackID").eq(0).text()
								+ "</trackID>");
						s.push("<startTime>"
								+ r.$XML(a).find("startTime").eq(0).text()
								+ "</startTime>");
						s.push("<endTime>"
								+ r.$XML(a).find("endTime").eq(0).text()
								+ "</endTime>");
						s.push("<playbackURI>"
								+ h.escape(r.$XML(a).find("playbackURI").eq(0)
										.text()) + "</playbackURI>");
						s.push("<metadataDescriptor>"
								+ r.$XML(a).find("metadataDescriptor").eq(0)
										.text().split("/")[1]
								+ "</metadataDescriptor>");
						s.push("</searchMatchItem>")
					}
					s.push("</matchList>");
					s.push("</CMSearchResult>");
					e = h.loadXML(s.join(""));
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			u.setRequestParam(c);
			u.submitRequest()
		};
		it.prototype.startPlayback = function(e, t) {
			var r = t.iWndIndex, s = t.szStartTime, n = t.szEndTime;
			var o = _(t.cgi, t.urlProtocol, e.szIP, t.iPort, t.iChannelID, s, n);
			var i = a.HWP_Play(o, e.szAuth, r, s, n);
			if (0 == i) {
				var u = new st;
				u.iIndex = r;
				u.szIP = e.szIP;
				u.iChannelID = t.iChannelID;
				u.iPlayStatus = T;
				l.push(u)
			}
			return i
		};
		it.prototype.reversePlayback = function(e, t) {
			var r = t.iWndIndex, s = t.szStartTime, n = t.szEndTime;
			var o = _(t.cgi, t.urlProtocol, e.szIP, t.iPort, t.iChannelID, s, n);
			var i = a.HWP_ReversePlay(o, e.szAuth, r, s, n);
			if (0 == i) {
				var u = new st;
				u.iIndex = r;
				u.szIP = e.szIP;
				u.iChannelID = t.iChannelID;
				u.iPlayStatus = A;
				l.push(u)
			}
			return i
		};
		it.prototype.startDownloadRecord = function(e, t) {
			var r = _(this.CGI.startDownloadRecord, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			var s = "<?xml version='1.0' encoding='UTF-8'?>"
					+ "<downloadRequest>" + "<playbackURI> "
					+ h.escape(t.szPlaybackURI) + "</playbackURI>"
					+ "</downloadRequest>";
			return a.HWP_StartDownload(r, e.szAuth, t.szFileName, s)
		};
		it.prototype.exportDeviceConfig = function(e) {
			var t = _(this.CGI.deviceConfig, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			return a.HWP_ExportDeviceConfig(t, e.szAuth, "", 0)
		};
		it.prototype.importDeviceConfig = function(e, t) {
			var r = _(this.CGI.deviceConfig, e.szHttpProtocol, e.szIP,
					e.iCGIPort);
			return a.HWP_ImportDeviceConfig(r, e.szAuth, t.szFileName, 0)
		};
		it.prototype.restart = function(e, t) {
			var r = _(this.CGI.restart, e.szHttpProtocol, e.szIP, e.iCGIPort);
			var s = new nt;
			var n = {
				type : "PUT",
				url : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(n, t);
			h.extend(n, {
				success : function(e) {
					if (t.success) {
						t.success(e)
					}
				},
				error : function(e, r) {
					if (t.error) {
						t.error(e, r)
					}
				}
			});
			s.setRequestParam(n);
			s.submitRequest()
		};
		it.prototype.restore = function(e, t, r) {
			var s = _(this.CGI.restore, e.szHttpProtocol, e.szIP, e.iCGIPort, t);
			var n = new nt;
			var o = {
				type : "PUT",
				url : s,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(o, r);
			h.extend(o, {
				success : function(e) {
					if (r.success) {
						r.success(e)
					}
				},
				error : function(e, t) {
					if (r.error) {
						r.error(e, t)
					}
				}
			});
			n.setRequestParam(o);
			n.submitRequest()
		};
		it.prototype.startUpgrade = function(e, t) {
			var r = _(this.CGI.startUpgrade.upgrade, e.szHttpProtocol, e.szIP,
					e.iCGIPort), s = _(this.CGI.startUpgrade.status,
					e.szHttpProtocol, e.szIP, e.iCGIPort);
			return a.HWP_StartUpgrade(r, s, e.szAuth, t.szFileName)
		};
		it.prototype.set3DZoom = function(e, t, r, s) {
			var n = _(this.CGI.set3DZoom, e.szHttpProtocol, e.szIP, e.iCGIPort,
					t.iChannelID);
			var o = new nt;
			var i = {
				type : "PUT",
				url : n,
				data : r,
				auth : e.szAuth,
				success : null,
				error : null
			};
			h.extend(i, s);
			h.extend(i, {
				success : function(e) {
					if (s.success) {
						s.success(e)
					}
				},
				error : function(e, t) {
					if (s.error) {
						s.error(e, t)
					}
				}
			});
			o.setRequestParam(i);
			o.submitRequest()
		};
		var at = function() {
		};
		at.prototype._alert = function(e) {
			if (i.bDebugMode) {
				console.log(e)
			}
		};
		(function(t) {
			var r = function(e) {
				this.elems = [];
				this.length = 0;
				this.length = this.elems.push(e)
			};
			r.prototype.find = function(e, t) {
				var r = this.elems[this.length - 1].getElementsByTagName(e);
				this.length = this.elems.push(r);
				if (t) {
					return r
				} else {
					return this
				}
			};
			r.prototype.eq = function(e, t) {
				var r = this.elems[this.length - 1].length, s = null;
				if (r > 0 && e < r) {
					s = this.elems[this.length - 1][e]
				}
				this.length = this.elems.push(s);
				if (t) {
					return s
				} else {
					return this
				}
			};
			r.prototype.text = function(t) {
				if (this.elems[this.length - 1]) {
					if (t) {
						if (e.DOMParser) {
							this.elems[this.length - 1].textContent = t
						} else {
							this.elems[this.length - 1].text = t
						}
					} else {
						if (e.DOMParser) {
							return this.elems[this.length - 1].textContent
						} else {
							return this.elems[this.length - 1].text
						}
					}
				} else {
					return ""
				}
			};
			r.prototype.attr = function(e) {
				if (this.elems[this.length - 1]) {
					var t = this.elems[this.length - 1].attributes
							.getNamedItem(e);
					if (t) {
						return t.value
					} else {
						return ""
					}
				}
			};
			t.$XML = function(e) {
				return new r(e)
			}
		})(this);
		var ut = function() {
		};
		ut.prototype.extend = function() {
			var e = arguments[0] || {}, t = 1, r = arguments.length, s;
			for (; t < r; t++) {
				if ((s = arguments[t]) != null) {
					for ( var n in s) {
						var o = e[n], i = s[n];
						if (e === i) {
							continue
						}
						if ("object" == typeof i) {
							e[n] = this.extend({}, i)
						} else if (i !== undefined) {
							e[n] = i
						}
					}
				}
			}
			return e
		};
		ut.prototype.browser = function() {
			var e = /(chrome)[ \/]([\w.]+)/;
			var t = /(safari)[ \/]([\w.]+)/;
			var r = /(opera)(?:.*version)?[ \/]([\w.]+)/;
			var s = /(msie) ([\w.]+)/;
			var n = /(trident.*rv:)([\w.]+)/;
			var o = /(mozilla)(?:.*? rv:([\w.]+))?/;
			var i = navigator.userAgent.toLowerCase();
			var a = e.exec(i) || t.exec(i) || r.exec(i) || s.exec(i)
					|| n.exec(i) || i.indexOf("compatible") < 0 && o.exec(i)
					|| [ "unknow", "0" ];
			if (a.length > 0 && a[1].indexOf("trident") > -1) {
				a[1] = "msie"
			}
			var u = {};
			u[a[1]] = true;
			u.version = a[2];
			return u
		};
		ut.prototype.loadXML = function(t) {
			if (null == t || "" == t) {
				return null
			}
			var r = null;
			if (e.DOMParser) {
				var s = new DOMParser;
				r = s.parseFromString(t, "text/xml")
			} else {
				r = new ActiveXObject("Microsoft.XMLDOM");
				r.async = false;
				r.loadXML(t)
			}
			return r
		};
		ut.prototype.toXMLStr = function(e) {
			var t = "";
			try {
				var r = new XMLSerializer;
				t = r.serializeToString(e)
			} catch (s) {
				try {
					t = e.xml
				} catch (s) {
					return ""
				}
			}
			if (t.indexOf("<?xml") == -1) {
				t = "<?xml version='1.0' encoding='utf-8'?>" + t
			}
			return t
		};
		ut.prototype.escape = function(e) {
			return e.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g,
					"&gt;")
		};
		ut.prototype.dateFormat = function(e, t) {
			var r = {
				"M+" : e.getMonth() + 1,
				"d+" : e.getDate(),
				"h+" : e.getHours(),
				"m+" : e.getMinutes(),
				"s+" : e.getSeconds(),
				"q+" : Math.floor((e.getMonth() + 3) / 3),
				S : e.getMilliseconds()
			};
			if (/(y+)/.test(t)) {
				t = t.replace(RegExp.$1, (e.getFullYear() + "")
						.substr(4 - RegExp.$1.length))
			}
			for ( var s in r) {
				if (new RegExp("(" + s + ")").test(t)) {
					t = t.replace(RegExp.$1, RegExp.$1.length == 1 ? r[s]
							: ("00" + r[s]).substr(("" + r[s]).length))
				}
			}
			return t
		};
		ut.prototype.Base64 = {
			_keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
			encode : function(e) {
				var t = "";
				var r, s, n, o, i, a, u;
				var c = 0;
				e = ut.prototype.Base64._utf8_encode(e);
				while (c < e.length) {
					r = e.charCodeAt(c++);
					s = e.charCodeAt(c++);
					n = e.charCodeAt(c++);
					o = r >> 2;
					i = (r & 3) << 4 | s >> 4;
					a = (s & 15) << 2 | n >> 6;
					u = n & 63;
					if (isNaN(s)) {
						a = u = 64
					} else if (isNaN(n)) {
						u = 64
					}
					t = t + this._keyStr.charAt(o) + this._keyStr.charAt(i)
							+ this._keyStr.charAt(a) + this._keyStr.charAt(u)
				}
				return t
			},
			decode : function(e) {
				var t = "";
				var r, s, n;
				var o, i, a, u;
				var c = 0;
				e = e.replace(/[^A-Za-z0-9\+\/\=]/g, "");
				while (c < e.length) {
					o = this._keyStr.indexOf(e.charAt(c++));
					i = this._keyStr.indexOf(e.charAt(c++));
					a = this._keyStr.indexOf(e.charAt(c++));
					u = this._keyStr.indexOf(e.charAt(c++));
					r = o << 2 | i >> 4;
					s = (i & 15) << 4 | a >> 2;
					n = (a & 3) << 6 | u;
					t = t + String.fromCharCode(r);
					if (a != 64) {
						t = t + String.fromCharCode(s)
					}
					if (u != 64) {
						t = t + String.fromCharCode(n)
					}
				}
				t = ut.prototype.Base64._utf8_decode(t);
				return t
			},
			_utf8_encode : function(e) {
				e = e.replace(/\r\n/g, "\n");
				var t = "";
				for (var r = 0; r < e.length; r++) {
					var s = e.charCodeAt(r);
					if (s < 128) {
						t += String.fromCharCode(s)
					} else if (s > 127 && s < 2048) {
						t += String.fromCharCode(s >> 6 | 192);
						t += String.fromCharCode(s & 63 | 128)
					} else {
						t += String.fromCharCode(s >> 12 | 224);
						t += String.fromCharCode(s >> 6 & 63 | 128);
						t += String.fromCharCode(s & 63 | 128)
					}
				}
				return t
			},
			_utf8_decode : function(e) {
				var t = "";
				var r = 0;
				var s = c1 = c2 = 0;
				while (r < e.length) {
					s = e.charCodeAt(r);
					if (s < 128) {
						t += String.fromCharCode(s);
						r++
					} else if (s > 191 && s < 224) {
						c2 = e.charCodeAt(r + 1);
						t += String.fromCharCode((s & 31) << 6 | c2 & 63);
						r += 2
					} else {
						c2 = e.charCodeAt(r + 1);
						c3 = e.charCodeAt(r + 2);
						t += String.fromCharCode((s & 15) << 12
								| (c2 & 63) << 6 | c3 & 63);
						r += 3
					}
				}
				return t
			}
		};
		function ct() {
			this.id = this.createUUID()
		}
		ct.prototype.valueOf = function() {
			return this.id
		};
		ct.prototype.toString = function() {
			return this.id
		};
		ct.prototype.createUUID = function() {
			var e = new Date(1582, 10, 15, 0, 0, 0, 0);
			var t = new Date;
			var r = t.getTime() - e.getTime();
			var s = "-";
			var n = ct.getIntegerBits(r, 0, 31);
			var o = ct.getIntegerBits(r, 32, 47);
			var i = ct.getIntegerBits(r, 48, 59) + "1";
			var a = ct.getIntegerBits(ct.rand(4095), 0, 7);
			var u = ct.getIntegerBits(ct.rand(4095), 0, 7);
			var c = ct.getIntegerBits(ct.rand(8191), 0, 7)
					+ ct.getIntegerBits(ct.rand(8191), 8, 15)
					+ ct.getIntegerBits(ct.rand(8191), 0, 7)
					+ ct.getIntegerBits(ct.rand(8191), 8, 15)
					+ ct.getIntegerBits(ct.rand(8191), 0, 15);
			return n + s + o + s + i + s + a + u + s + c
		};
		ct.getIntegerBits = function(e, t, r) {
			var s = ct.returnBase(e, 16);
			var n = new Array;
			var o = "";
			var i = 0;
			for (i = 0; i < s.length; i++) {
				n.push(s.substring(i, i + 1))
			}
			for (i = Math.floor(t / 4); i <= Math.floor(r / 4); i++) {
				if (!n[i] || n[i] == "")
					o += "0";
				else
					o += n[i]
			}
			return o
		};
		ct.returnBase = function(e, t) {
			var r = [ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
					"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
					"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
					"Z" ];
			if (e < t)
				var s = r[e];
			else {
				var n = "" + Math.floor(e / t);
				var o = e - n * t;
				if (n >= t)
					var s = this.returnBase(n, t) + r[o];
				else
					var s = r[n] + r[o]
			}
			return s
		};
		ct.rand = function(e) {
			return Math.floor(Math.random() * e)
		};
		f = new ot;
		P = new it;
		d = new at;
		h = new ut;
		var lt = h.dateFormat(new Date, "yyyyMMddhhmmss");
		n = "webVideoCtrl" + lt;
		o = "webVideoCtrl" + lt;
		if (typeof e.attachEvent != "object" && h.browser().msie) {
			var pt = "<script for="
					+ n
					+ " event='GetSelectWndInfo(SelectWndInfo)'>GetSelectWndInfo(SelectWndInfo);</script>";
			pt += "<script for="
					+ n
					+ " event='ZoomInfoCallback(szZoomInfo)'>ZoomInfoCallback(szZoomInfo);</script>";
			pt += "<script for="
					+ n
					+ "  event='GetHttpInfo(lID, lpInfo, lReverse)'>GetHttpInfo(lID, lpInfo, lReverse);</script>";
			pt += "<script for="
					+ n
					+ "  event='PluginEventHandler(iEventType, iParam1, iParam2)'>PluginEventHandler(iEventType, iParam1, iParam2);</script>";
			document.write(pt)
		}
		return this
	}();
	var r = e.WebVideoCtrl = t;
	r.version = "1.0.9"
})(this);