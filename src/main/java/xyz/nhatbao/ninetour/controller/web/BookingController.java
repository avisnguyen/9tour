package xyz.nhatbao.ninetour.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.nhatbao.ninetour.model.request.BookingRequestModel;
import xyz.nhatbao.ninetour.model.request.TripRequestModel;
import xyz.nhatbao.ninetour.model.response.BookingResponseModel;
import xyz.nhatbao.ninetour.model.response.ExtraServiceResponseModel;
import xyz.nhatbao.ninetour.model.response.TripResponseModel;
import xyz.nhatbao.ninetour.service.BookingService;
import xyz.nhatbao.ninetour.service.ExtraServiceService;
import xyz.nhatbao.ninetour.service.TripService;
import xyz.nhatbao.ninetour.vnpay.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/*******************************************************************************
 <pre>

 Copyright (c) 2021 Nguyen Nhat Bao
 This project is licensed under the terms of the MIT license.

 Author: Nguyen Nhat Bao (Kian Nguyen)
 Website: https://kiandev.xyz
 Contact for work: kiannguyen.work@gmail.com
 Feedback to me: kiannguyen.dev@gmail.com
 Github: https://github.com/kian-nguyen

 Please do not remove.

 </pre>
 ******************************************************************************/

@Controller
@RequestMapping("/tour/booking")
public class BookingController {

    @Autowired
    private TripService tripService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ExtraServiceService extraService;

    Date date = new Date();

    @GetMapping("/{tripId}/pax-no")
    public ModelAndView bookingFirstPage(@PathVariable Long tripId, HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("layouts/web/booking/booking-1");
        session.invalidate();
        HttpSession newSession = request.getSession();
        System.out.println(newSession.getId());
        TripResponseModel tripResponseModel = new TripResponseModel();
        BookingResponseModel bookingResponseModel = new BookingResponseModel();
        ExtraServiceResponseModel extraServiceResponseModel = new ExtraServiceResponseModel();
        if (tripId != null) {
            tripResponseModel = tripService.findAllById(tripId);
            extraServiceResponseModel = extraService.findServiceByTourId(tripResponseModel.getTourResponseModel().getId());
        }
        if (tripResponseModel.getDepartureTime().compareTo(date) >= 0 && tripResponseModel.getAdultAvailable() >= 1) {
            modelAndView.addObject("pageTitle", "Booking - Chọn dịch vụ");
            modelAndView.addObject("trip", tripResponseModel);
            modelAndView.addObject("services", extraServiceResponseModel);
            modelAndView.addObject("booking", bookingResponseModel);
            modelAndView.addObject("tripRequest", new TripRequestModel());
            modelAndView.addObject("bookingRequest", new BookingRequestModel());
            return modelAndView;
        }
        return new ModelAndView("redirect:/error");
    }

    @PostMapping("/process/pax-no")
    public String processPaxNo(@ModelAttribute("bookingRequest") BookingRequestModel bookingRequestModel,
                               @RequestParam("sessionId") String sessionId,
                               RedirectAttributes redirectAttributes,
                               HttpServletRequest request) {
        if (request.getSession().getId().equals(sessionId)) {
            BookingResponseModel bookingResponseModel = bookingService.setPaxNo(bookingRequestModel);
            redirectAttributes.addFlashAttribute("booking", bookingResponseModel);
            request.getSession().setAttribute("booking", bookingResponseModel);
            return "redirect:/tour/booking/" + bookingRequestModel.getTripId().toString() + "/passengers?sessionId=" + request.getSession().getId();
        }
        return "redirect:/error";
    }

    @GetMapping("/{tripId}/passengers")
    public ModelAndView bookingSecondPage(@PathVariable Long tripId,
                                          @RequestParam("sessionId") String sessionId,
                                          @ModelAttribute("booking") BookingResponseModel bookingResponseModel,
                                          HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (request.getSession().getId().equals(sessionId)) {
            TripResponseModel tripResponseModel = new TripResponseModel();
            if (tripId != null) {
                tripResponseModel = tripService.findAllById(tripId);
            }
            if (tripResponseModel.getDepartureTime().compareTo(date) >= 0 && tripResponseModel.getAdultAvailable() >= 1) {
                modelAndView.setViewName("layouts/web/booking/booking-2");
                modelAndView.addObject("pageTitle", "Booking - Nhập thông tin hành khách");
                modelAndView.addObject("trip", tripResponseModel);
                modelAndView.addObject("booking", request.getSession().getAttribute("booking"));
                return modelAndView;
            } else return new ModelAndView("redirect:/error");
        }
        return new ModelAndView("redirect:/error");
    }

    @PostMapping("/process/passengers")
    public String processPassengers(@ModelAttribute("booking") BookingRequestModel bookingRequestModel,
                                    @RequestParam("sessionId") String sessionId,
                                    RedirectAttributes redirectAttributes,
                                    HttpServletRequest request) {
        if (request.getSession().getId().equals(sessionId)) {
            BookingResponseModel bookingResponseModel = bookingService.setPassengers(bookingRequestModel, request);
            redirectAttributes.addFlashAttribute("booking", bookingResponseModel);
            request.getSession().setAttribute("booking", bookingResponseModel);
            return "redirect:/tour/booking/" + bookingRequestModel.getTripId().toString() + "/confirm?sessionId=" + request.getSession().getId();
        }
        return "redirect/error";
    }

    @GetMapping("/{tripId}/confirm")
    public ModelAndView bookingThirdPage(@PathVariable Long tripId,
                                         @RequestParam("sessionId") String sessionId,
                                         @ModelAttribute("booking") BookingResponseModel bookingResponseModel,
                                         HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (request.getSession().getId().equals(sessionId)) {
            TripResponseModel tripResponseModel = new TripResponseModel();
            if (tripId != null) {
                tripResponseModel = tripService.findAllById(tripId);
            }
            if (tripResponseModel.getDepartureTime().compareTo(date) >= 0 && tripResponseModel.getAdultAvailable() >= 1) {
                modelAndView.setViewName("layouts/web/booking/booking-3");
                modelAndView.addObject("pageTitle", "Booking - Xác nhận thông tin");
                modelAndView.addObject("trip", tripResponseModel);
                modelAndView.addObject("booking", request.getSession().getAttribute("booking"));
                return modelAndView;
            } else return new ModelAndView("redirect:/error");
        }
        return new ModelAndView("redirect:/error");
    }

    @GetMapping("/process")
    public ModelAndView resultPage(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
        ModelAndView modelAndView = new ModelAndView();
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = (String) params.nextElement();
            String fieldValue = request.getParameter(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = Config.hashAllFields(fields);
        modelAndView.addObject("signValue", signValue.equals(vnp_SecureHash));
        int amount = Integer.parseInt(request.getParameter("vnp_Amount")) / 100;
        Date payDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(request.getParameter("vnp_PayDate"));
        if (signValue.equals(vnp_SecureHash)) {
            //Kiem tra chu ky OK
        /* Kiem tra trang thai don hang trong DB: checkOrderStatus,
        - Neu trang thai don hang OK, tien hanh cap nhat vao DB, tra lai cho VNPAY RspCode=00
        - Neu trang thai don hang (da cap nhat roi) => khong cap nhat vao DB, tra lai cho VNPAY RspCode=02
         */
            boolean checkOrderStatus = true;
            if (checkOrderStatus) {
                if ("00".equals(request.getParameter("vnp_ResponseCode"))) {
                    //Xu ly thanh toan thanh cong
                    // out.print("GD Thanh cong");
                    bookingService.setPaymentLog(request);
                    modelAndView.setViewName("layouts/web/booking/booking-success");
                    modelAndView.addObject("pageTitle", "Giao dịch thành công");
                    modelAndView.addObject("amount", amount);
                    modelAndView.addObject("payDate", payDate);
                } else {
                    //Xu ly thanh toan khong thanh cong
                    //  out.print("GD Khong thanh cong");
                    modelAndView.setViewName("layouts/web/booking/booking-fail");
                    modelAndView.addObject("pageTitle", "Giao dịch thất bại");

                }
                System.out.println("{\"RspCode\":\"00\",\"Message\":\"Confirm Success\"}");
            } else {
                //Don hang nay da duoc cap nhat roi, Merchant khong cap nhat nua (Duplicate callback)
                System.out.println("{\"RspCode\":\"02\",\"Message\":\"Order already confirmed\"}");
                modelAndView.setViewName("layouts/web/booking/booking-fail");
                modelAndView.addObject("pageTitle", "Giao dịch thất bại");
            }

        } else {
            System.out.println("{\"RspCode\":\"97\",\"Message\":\"Invalid Checksum\"}");
            modelAndView.setViewName("layouts/web/booking/booking-fail");
            modelAndView.addObject("pageTitle", "Giao dịch thất bại");
        }
        return modelAndView;
    }
//    @GetMapping("/tour/pay/success")
//    public ModelAndView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
//        ModelAndView modelAndView = new ModelAndView("layouts/web/booking/booking-success");
//        try {
//            Payment payment = paypalService.executePayment(paymentId, payerId);
//            if(payment.getState().equals("approved")){
//                return modelAndView;
//            }
//        } catch (PayPalRESTException e) {
//            log.error(e.getMessage());
//        }
//        return modelAndView;
//    }
//    @GetMapping("/tour/pay/cancel")
//    public ModelAndView cancelPay(){
//        ModelAndView modelAndView = new ModelAndView("layouts/web/booking/booking-fail");
//        return modelAndView;
//    }
}
