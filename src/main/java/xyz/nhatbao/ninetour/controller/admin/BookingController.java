package xyz.nhatbao.ninetour.controller.admin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.request.BillRequestModel;
import xyz.nhatbao.ninetour.model.request.BookingRequestModel;
import xyz.nhatbao.ninetour.model.response.BillResponseModel;
import xyz.nhatbao.ninetour.model.response.PaymentLogResponseModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;
import xyz.nhatbao.ninetour.model.response.TripResponseModel;
import xyz.nhatbao.ninetour.other.BillExcelExporter;
import xyz.nhatbao.ninetour.security.mvc.SecurityUtil;
import xyz.nhatbao.ninetour.service.*;
import xyz.nhatbao.ninetour.util.MessageUtil;
import xyz.nhatbao.ninetour.util.PageUtil;
import xyz.nhatbao.ninetour.vnpay.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

@Controller("bookingAdminController")
@RequestMapping("/admin/booking")
public class BookingController {
    @Autowired
    BillService billService;

    @Autowired
    TourService tourService;

    @Autowired
    AdminBookingService adminBookingService;

    @Autowired
    BookingService bookingService;

    @Autowired
    TripService tripService;

    @Autowired
    UserService userService;

    @Autowired
    PlaceService placeService;

    PageUtil pageUtil = new PageUtil();

    MessageUtil messageUtil = new MessageUtil();

    @Value("${server.servlet.context-path}")
    String contextPath;

    @GetMapping("/list")
    ModelAndView listBill(@RequestParam(value = "tour", required = false) Long tourId,
                          @RequestParam(value = "trip", required = false) Long tripId,
                          @RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date from,
                          @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date to,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          @RequestParam(value = "sort", required = false) String sortBy,
                          @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                          @ModelAttribute(value = "ref") String ref,
                          @RequestHeader(value = "referer", required = false) String referer,
                          @ModelAttribute("message") String message) throws URISyntaxException {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/booking/list");
        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        if (tripId != null) {
            keyword = null;
            from = to = null;
        }
        BillResponseModel billResponseModel = new BillResponseModel();
        billResponseModel = billService.searchBill(tourId, tripId, keyword, from, to, pageable);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(billResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        billResponseModel.setPageAndSort(page, limit, sortBy, desc);

        TourResponseModel tourResponseModel = tourService.getAll();
        JSONArray allTours = new JSONArray();
        for (TourResponseModel tour :
                tourResponseModel.getResults()) {
            Map<String, String> jsonTour = new HashMap<>();
            jsonTour.put("name", tour.getName());
            jsonTour.put("id", tour.getId().toString());
            allTours.put(new JSONObject(jsonTour));
        }

        TripResponseModel tripResponseModel = new TripResponseModel();
        if (tourId != null) {
            tripResponseModel.setResults(tourService.findById(tourId).getTripResponseModels());
        } else {
            tripResponseModel = tripService.getAll();
        }

        SimpleDateFormat tripFormat = new SimpleDateFormat("dd-MM-yyyy");

        JSONArray allTrips = new JSONArray();
        for (TripResponseModel trip :
                tripResponseModel.getResults()) {
            Map<String, String> jsonTrip = new HashMap<>();
            jsonTrip.put("code", trip.getCode());
            jsonTrip.put("date", tripFormat.format(trip.getDepartureTime()));
            jsonTrip.put("id", trip.getId().toString());
            allTrips.put(new JSONObject(jsonTrip));
        }

        if (tourId != null && tripId == null) {
            TourResponseModel selectedTour = tourService.findById(tourId);
            modelAndView.addObject("tour", selectedTour);
        }

        if (tripId != null) {
            TripResponseModel selectedTrip = tripService.findById(tripId);
            modelAndView.addObject("trip", selectedTrip);

            TourResponseModel selectedTour = selectedTrip.getTourResponseModel();
            modelAndView.addObject("tour", selectedTour);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        if (from != null) {
            String fromDate = format.format(from);
            modelAndView.addObject("from", fromDate);
        }
        if (to != null) {
            String toDate = format.format(to);
            modelAndView.addObject("to", toDate);
        }

        //Add referer
        if (!ref.equals("")) {
            modelAndView.addObject("ref", ref);
        } else {
            try {
                URI referentURI = new URI(referer);
                referer = referentURI.getPath() + "?" + referentURI.getQuery();
            } catch (NullPointerException e) {
                referer = "";
            }
            modelAndView.addObject("ref", referer);
        }

        modelAndView.addObject("billRequestModel", new BillRequestModel());
        modelAndView.addObject("billModel", billResponseModel);
        modelAndView.addObject("allTours", allTours);
        modelAndView.addObject("allTrips", allTrips);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("message", messageModel);
        return modelAndView;
    }

    @GetMapping(value = {"/add", "/edit"})
    ModelAndView editBill(@RequestParam(value = "id", required = false) Long id,
                          @RequestParam(value = "tourId", required = false) Long tourId,
                          @RequestParam(value = "tripId", required = false) Long tripId,
                          @ModelAttribute(value = "ref") String ref,
                          @RequestHeader(value = "referer", required = false) String referer,
                          @ModelAttribute("message") String message) throws URISyntaxException {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/booking/edit");
        BillResponseModel billResponseModel = new BillResponseModel();
        if (id != null) billResponseModel = billService.findById(id);

        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(billResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);

        TourResponseModel tourResponseModel = tourService.getAll();
        JSONArray allTours = new JSONArray();
        for (TourResponseModel tour :
                tourResponseModel.getResults()) {
            Map<String, String> jsonTour = new HashMap<>();
            jsonTour.put("name", tour.getName());
            jsonTour.put("id", tour.getId().toString());
            allTours.put(new JSONObject(jsonTour));
        }
        modelAndView.addObject("allTours", allTours);

        TripResponseModel tripResponseModel = new TripResponseModel();
        if (tourId != null) {
            TourResponseModel tour = tourService.findById(tourId);
            modelAndView.addObject("tour", tour);
            tripResponseModel.setResults(tour.getTripResponseModels());
        } else {
            tripResponseModel = tripService.getAll();
            modelAndView.addObject("tour", new TourResponseModel());
        }

        if (tripId != null) {
            modelAndView.addObject("trip", tripService.findById(tripId));
        } else modelAndView.addObject("trip", new TripResponseModel());

        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        JSONArray allTrips = new JSONArray();
        for (TripResponseModel trip :
                tripResponseModel.getResults()) {
            Map<String, String> jsonTrip = new HashMap<>();
            jsonTrip.put("code", trip.getCode());
            jsonTrip.put("date", formater.format(trip.getDepartureTime()));
            jsonTrip.put("id", trip.getId().toString());
            allTrips.put(new JSONObject(jsonTrip));
        }
        modelAndView.addObject("allTrips", allTrips);

        //Add referer
        if (!ref.equals("")) {
            modelAndView.addObject("ref", ref);
        } else {
            try {
                URI referentURI = new URI(referer);
                referer = referentURI.getPath() + "?" + referentURI.getQuery();
            } catch (NullPointerException e) {
                referer = "";
            }
            modelAndView.addObject("ref", referer);
        }

        modelAndView.addObject("billModel", billResponseModel);
        modelAndView.addObject("message", messageModel);
        modelAndView.addObject("bookingRequestModel", new BookingRequestModel());
        return modelAndView;
    }

    @PostMapping(value = {"/add", "/edit"})
    ModelAndView processEditBill(@Valid @ModelAttribute("bookingRequestModel") BookingRequestModel bookingRequestModel,
                                 @ModelAttribute("ref") String ref
    ) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        BillResponseModel billResponseModel = new BillResponseModel();
        BillRequestModel billRequestModel = new BillRequestModel();
        bookingRequestModel.setUserId(SecurityUtil.getUserPrincipal().getId());
        if (bookingRequestModel.getBillId() != null) {
            billResponseModel = adminBookingService.update(bookingRequestModel.getBillId(), bookingRequestModel);
            modelAndView.setViewName("redirect:/admin/booking/edit?id=" + billResponseModel.getId().toString());

        } else {
            billResponseModel = adminBookingService.order(bookingRequestModel);
            if (billResponseModel.getMessage().equals("insert_success")) {
                modelAndView.setViewName("redirect:/admin/booking/edit?id=" + billResponseModel.getId().toString());
                if (!ref.equals("")) modelAndView.setViewName("redirect:" + ref.substring(contextPath.length()));
            } else modelAndView.setViewName("redirect:/admin/booking/add");
        }
        if (!ref.equals("")) modelAndView.addObject("ref", ref);
        modelAndView.addObject("message", billResponseModel.getMessage());
        return modelAndView;
    }

    @PostMapping(value = {"/delete"})
    public ModelAndView processDeleteBill(@ModelAttribute BillRequestModel billRequestModel,
                                          @ModelAttribute("ref") String ref) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/booking/list");
        if (!ref.equals("")) modelAndView.setViewName("redirect:" + ref.substring(contextPath.length()));
        BillResponseModel billResponseModel = new BillResponseModel();
        List<Long> deleteList = billRequestModel.getIds();
        if (deleteList != null) billResponseModel = billService.delete(billRequestModel);
        if (!ref.equals("")) modelAndView.addObject("ref", ref);
        modelAndView.addObject("message", billResponseModel.getMessage());
        return modelAndView;
    }

    @GetMapping("/export")
    public void userExport(HttpServletResponse response,
                           @RequestParam(value = "tour", required = false) Long tourId,
                           @RequestParam(value = "trip", required = false) Long tripId,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date from,
                           @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date to,
                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                           @RequestParam(value = "sort", required = false) String sortBy,
                           @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                           @ModelAttribute(value = "ref") String ref,
                           @RequestHeader(value = "referer", required = false) String referer) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=bills_" + currentTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        BillResponseModel bills = billService.getAll();

        BillExcelExporter excelExporter = new BillExcelExporter(bills.getResults());
        excelExporter.export(response);
    }

    @GetMapping("/refund/list")
    ModelAndView listRefundRequest(@RequestParam(value = "tour", required = false) Long tourId,
                                   @RequestParam(value = "trip", required = false) Long tripId,
                                   @RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date from,
                                   @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date to,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                   @RequestParam(value = "sort", required = false) String sortBy,
                                   @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                                   @ModelAttribute(value = "ref") String ref,
                                   @RequestHeader(value = "referer", required = false) String referer,
                                   @ModelAttribute("message") String message) throws URISyntaxException {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/booking/list");
        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        if (tripId != null) {
            keyword = null;
            from = to = null;
        }
        BillResponseModel billResponseModel = new BillResponseModel();
        billResponseModel = billService.searchRefundRequestBill(tourId, tripId, keyword, from, to, pageable);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(billResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        billResponseModel.setPageAndSort(page, limit, sortBy, desc);

        TourResponseModel tourResponseModel = tourService.getAll();
        JSONArray allTours = new JSONArray();
        for (TourResponseModel tour :
                tourResponseModel.getResults()) {
            Map<String, String> jsonTour = new HashMap<>();
            jsonTour.put("name", tour.getName());
            jsonTour.put("id", tour.getId().toString());
            allTours.put(new JSONObject(jsonTour));
        }

        TripResponseModel tripResponseModel = new TripResponseModel();
        if (tourId != null) {
            tripResponseModel.setResults(tourService.findById(tourId).getTripResponseModels());
        } else {
            tripResponseModel = tripService.getAll();
        }

        SimpleDateFormat tripFormat = new SimpleDateFormat("dd-MM-yyyy");

        JSONArray allTrips = new JSONArray();
        for (TripResponseModel trip :
                tripResponseModel.getResults()) {
            Map<String, String> jsonTrip = new HashMap<>();
            jsonTrip.put("code", trip.getCode());
            jsonTrip.put("date", tripFormat.format(trip.getDepartureTime()));
            jsonTrip.put("id", trip.getId().toString());
            allTrips.put(new JSONObject(jsonTrip));
        }

        if (tourId != null && tripId == null) {
            TourResponseModel selectedTour = tourService.findById(tourId);
            modelAndView.addObject("tour", selectedTour);
        }

        if (tripId != null) {
            TripResponseModel selectedTrip = tripService.findById(tripId);
            modelAndView.addObject("trip", selectedTrip);

            TourResponseModel selectedTour = selectedTrip.getTourResponseModel();
            modelAndView.addObject("tour", selectedTour);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        if (from != null) {
            String fromDate = format.format(from);
            modelAndView.addObject("from", fromDate);
        }
        if (to != null) {
            String toDate = format.format(to);
            modelAndView.addObject("to", toDate);
        }

        //Add referer
        if (!ref.equals("")) {
            modelAndView.addObject("ref", ref);
        } else {
            try {
                URI referentURI = new URI(referer);
                referer = referentURI.getPath() + "?" + referentURI.getQuery();
            } catch (NullPointerException e) {
                referer = "";
            }
            modelAndView.addObject("ref", referer);
        }

        modelAndView.addObject("billRequestModel", new BillRequestModel());
        modelAndView.addObject("billModel", billResponseModel);
        modelAndView.addObject("allTours", allTours);
        modelAndView.addObject("allTrips", allTrips);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("message", messageModel);
        return modelAndView;
    }

    @GetMapping("/{billId}/pay")
    public ModelAndView payment(@PathVariable("billId") Long billId, HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/booking/pay");
        BillResponseModel billResponseModel = billService.showBill(billId);
        if (request.getParameter("vnp_TransactionNo") != null && !request.getParameter("vnp_TransactionNo").equals("")) {
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
                        PaymentLogResponseModel paymentLogResponseModel = new PaymentLogResponseModel();
                        paymentLogResponseModel.setAmount(Double.parseDouble(request.getParameter("vnp_Amount")) / 100);
                        billResponseModel.getPaymentLogResponseModels().add(paymentLogResponseModel);
                        modelAndView.addObject("bill", billResponseModel);
                        Map<String, String> message = new HashMap<>();
                        message.put("msg", "Giao dịch thành công!");
                        message.put("alert", "alert-success");
                        modelAndView.addObject("message");
                        modelAndView.addObject("pageTitle", "Giao dịch thành công");
                        modelAndView.addObject("amount", amount);
                        modelAndView.addObject("payDate", payDate);
                    } else {
                        //Xu ly thanh toan khong thanh cong
                        //  out.print("GD Khong thanh cong");
                        Map<String, String> message = new HashMap<>();
                        message.put("msg", "Giao dịch thất bại!");
                        message.put("alert", "alert-danger");
                        modelAndView.addObject("message");
                        modelAndView.addObject("bill", billResponseModel);
                        modelAndView.addObject("pageTitle", "Giao dịch thất bại");

                    }
                } else {
                    //Don hang nay da duoc cap nhat roi, Merchant khong cap nhat nua (Duplicate callback)
                    Map<String, String> message = new HashMap<>();
                    message.put("msg", "Giao dịch thất bại!");
                    message.put("alert", "alert-danger");
                    modelAndView.addObject("message");
                    modelAndView.addObject("bill", billResponseModel);
                    modelAndView.addObject("pageTitle", "Giao dịch thất bại");
                }

            } else {
                Map<String, String> message = new HashMap<>();
                message.put("msg", "Giao dịch thất bại!");
                message.put("alert", "alert-danger");
                modelAndView.addObject("message");
                modelAndView.addObject("bill", billResponseModel);
                modelAndView.addObject("pageTitle", "Giao dịch thất bại");
            }
        } else {
            modelAndView.setViewName("layouts/admin/booking/pay");
            modelAndView.addObject("bill", billResponseModel);
            modelAndView.addObject("pageTitle", "Thanh toán");
            request.getSession().setAttribute("billId", billResponseModel.getId());
        }
        return modelAndView;
    }
}
